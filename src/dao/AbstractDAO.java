package dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import model.Entity;
import util.BeanUtils;
import util.StringUtils;
import connection.SingleConnection;
import exception.MissingAnnotationException;

public class AbstractDAO<T extends Entity> implements DAO<T> {
	private final SingleConnection conn;
	private final Class<T> entityClass;

	public AbstractDAO(final Class<T> clazz) throws Exception {
		this.entityClass = clazz;
		if (!this.entityClass.isAnnotationPresent(annotation.Entity.class))
			throw new MissingAnnotationException("Entity");

		this.conn = SingleConnection.getInstance();
	}

	@Override
	public T getById(final Integer id) throws Exception {

		final Field[] entityFields = this.getEntityDeclaredFields();
		Field entityId = null;
		for (final Field f : entityFields)
			if (f.isAnnotationPresent(annotation.Id.class))
				entityId = f;

		if (entityId == null)
			throw new MissingAnnotationException("Id");

		return getByField(entityId.getName(), id.toString()).get(0);
	}

	@Override
	public List<T> list() throws Exception {
		final String query = "SELECT * FROM "
				+ StringUtils.camelCaseToUnderscore(this.entityClass
						.getSimpleName().toLowerCase());
		final PreparedStatement ps = this.conn.prepareStatement(query);

		final ResultSet rs = ps.executeQuery();

		final List<T> results = this.getListOfBeansFromResultSet(rs);

		return results;
	}

	@Override
	public void saveOrUpdate(final T obj) throws Exception {
	}

	private Field[] getEntityDeclaredFields() {
		return this.entityClass.getDeclaredFields();
	}

	protected List<T> getByField(String field, String value) throws Exception {
		String query = "SELECT * FROM "
				+ StringUtils.camelCaseToUnderscore(this.entityClass
						.getSimpleName()) + " WHERE "
				+ StringUtils.camelCaseToUnderscore(field)
				+ " = (?)";
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
					final Object columnValue = rs.getObject(_iterator + 1);

					for (final Field field : this.getEntityDeclaredFields())
						if (field.getName().equalsIgnoreCase(columnName)
								&& columnValue != null) {
							BeanUtils.setProperty(bean, field.getName(),
									columnValue);
							break;
						}
				}
				list.add(bean);
			}
		}
		return list;
	}

}
