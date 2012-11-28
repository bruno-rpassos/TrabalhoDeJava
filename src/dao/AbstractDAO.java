package dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Entity;
import util.BeanUtils;
import util.StringUtils;
import annotation.JoinTable;
import annotation.Table;
import connection.SingleConnection;
import exception.MissingAnnotationException;

public class AbstractDAO<T extends Entity> implements DAO<T> {
	private final SingleConnection conn;
	private final Class<T> entityClass;
	private final String entityName;
	private Field entityId;

	public AbstractDAO(final Class<T> clazz) throws Exception {
		this.entityClass = clazz;
		if (!this.entityClass.isAnnotationPresent(annotation.Entity.class)) {
			throw new MissingAnnotationException("Entity");
		}

		if (this.entityClass.isAnnotationPresent(annotation.Table.class)) {
			Table t = this.entityClass.getAnnotation(annotation.Table.class);
			this.entityName = t.name();
		} else {
			this.entityName = StringUtils
					.camelCaseToUnderscore(this.entityClass.getSimpleName()
							.toLowerCase());
		}

		for (final Field f : this.getEntityDeclaredFields()) {
			if (f.isAnnotationPresent(annotation.Id.class)) {
				entityId = f;
			}
		}

		if (entityId == null)
			throw new MissingAnnotationException("Id");

		this.conn = SingleConnection.getInstance();
	}

	@Override
	public T getById(final Integer id) throws Exception {
		return getByField(entityId.getName(), id).get(0);
	}

	@Override
	public List<T> list() throws Exception {
		final String query = "SELECT * FROM " + this.entityName;
		PreparedStatement ps = this.conn.prepareStatement(query);
		
		System.out.println(query);
		ResultSet rs = ps.executeQuery();

		List<T> results = this.getListOfBeansFromResultSet(rs);

		return results;
	}

	@Override
	public void saveOrUpdate(final T obj) throws Exception {
		Field[] fields = this.entityClass.getDeclaredFields();
		StringBuilder columns = new StringBuilder();
		List values = new ArrayList();

		for (Field f : fields) {
			if (!f.isAnnotationPresent(annotation.Transient.class)
					&& !f.isAnnotationPresent(annotation.ManyToMany.class)) {
				f.setAccessible(true);
				String columnName = f.getName();
				Object value = f.get(obj);
				if (obj.getId() == null
						&& f.isAnnotationPresent(annotation.Id.class))
					continue;
				if(value.getClass().isAnnotationPresent(annotation.Entity.class)) {
					value = ((Entity) value).getId();
					columnName = columnName + "Id";
				}
				columns.append(","
						+ StringUtils.camelCaseToUnderscore(columnName));
				
				values.add(value);
				f.setAccessible(false);
			}
		}

		columns.setCharAt(0, ' ');
		
		StringBuilder valuesToReplace = new StringBuilder();
		
		for(Object o : values) {
			valuesToReplace.append(",?");
		}
		
		valuesToReplace.setCharAt(0, ' ');

		StringBuilder query = new StringBuilder("INSERT INTO "
				+ this.entityName + "(" + columns + " ) values (" + valuesToReplace
				+ " )");
		
		PreparedStatement ps = this.conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
		for(int i=0;i<values.size();i++) {
			ps.setObject(i+1, values.get(i));
		}
		
		int affectedRows = ps.executeUpdate();
		if(affectedRows == 0) {
			throw new SQLException("Houve um erro ao gravar o registro");
		}
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			obj.setId(generatedKeys.getInt(1));
		} else {
			throw new SQLException("Houve um erro ao recuperar a chave o registro");
		}
		
		PreparedStatement associationPs = null;

		for (Field f : fields) {
			if (f.isAnnotationPresent(annotation.ManyToMany.class)) {
				JoinTable joinTableAnnotation = f
						.getAnnotation(annotation.JoinTable.class);

				f.setAccessible(true);
				@SuppressWarnings("unchecked")
				Collection<Entity> associationObjects = (Collection<Entity>) f
						.get(obj);

				for (Entity e : associationObjects) {
					StringBuilder associateQuery = new StringBuilder(
							"INSERT INTO "
									+ joinTableAnnotation.name()
									+ " ( "
									+ this.entityName
									+ "_id, "
									+ e.getClass().getSimpleName()
											.toLowerCase() + "_id ) values ( "
									+ obj.getId() + ", " + e.getId() + " )");
					associationPs = this.conn.prepareStatement(associateQuery.toString());
					int affectedAssociationRows = associationPs.executeUpdate();
					if(affectedAssociationRows == 0) {
						throw new SQLException("Houve um erro ao gravar as associações");
					}
				}
			}
		}
		
		if(associationPs != null) ps.close();
		
		if(ps != null) ps.close();
		if(generatedKeys != null) generatedKeys.close();
		
		this.conn.commit();
	}

	private Field[] getEntityDeclaredFields() {
		return this.entityClass.getDeclaredFields();
	}

	protected List<T> getByField(String field, Object value) throws Exception {
		String query = "SELECT * FROM " + this.entityName + " WHERE "
				+ StringUtils.camelCaseToUnderscore(field) + " = (?)";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setObject(1, value);

		ResultSet rs = ps.executeQuery();
		List<T> results = getListOfBeansFromResultSet(rs);

		return results;
	}

	private List<T> getListOfBeansFromResultSet(final ResultSet rs)
			throws Exception {
		final List<T> list = new ArrayList<T>();
		final ResultSetMetaData rsmd = rs.getMetaData();

		if (rs != null) {
			while (rs.next()) {
				final T bean = this.entityClass.newInstance();
				for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
					final String columnName = StringUtils
							.underscoreToCamelCase(rsmd
									.getColumnName(_iterator + 1));
					Object columnValue = rs.getObject(_iterator + 1);

					for (final Field field : this.getEntityDeclaredFields()) {
						String fieldNameInDB = field.getName();

						if (field
								.isAnnotationPresent(annotation.BelongsTo.class)) {
							fieldNameInDB = fieldNameInDB + "Id";
						}

						if (fieldNameInDB.equalsIgnoreCase(columnName)
								&& columnValue != null
								&& !field
										.isAnnotationPresent(annotation.Transient.class)) {

							if (field
									.isAnnotationPresent(annotation.BelongsTo.class)) {
								@SuppressWarnings({ "rawtypes", "unchecked" })
								AbstractDAO absDAO = new AbstractDAO(
										field.getType());
								@SuppressWarnings("rawtypes")
								List relation = absDAO.getByField("id",
										new Integer(columnValue.toString()));
								columnValue = relation.size() >= 1 ? relation
										.get(0) : null;
							}

							BeanUtils.setProperty(bean, field.getName(),
									columnValue);

							break;
						}
					}
				}
				list.add(bean);
			}
		}
		return list;
	}

}
