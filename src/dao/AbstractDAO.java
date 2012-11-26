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

public class AbstractDAO<T extends Entity> implements
		DAO<T> {
	private Class<T> entityClass;
	private SingleConnection conn;

	public AbstractDAO(Class<T> clazz) throws Exception {
		this.entityClass = clazz;
		if (!this.entityClass.isAnnotationPresent(annotation.Entity.class)) {
			throw new MissingAnnotationException("Entity");
		}

		this.conn = SingleConnection.getInstance();
	}

	@Override
	public T getById(Integer id) throws Exception {

		Field[] entityFields = getEntityDeclaredFields();
		Field entityId = null;
		for (Field f : entityFields) {

			if (f.isAnnotationPresent(annotation.Id.class)) {
				entityId = f;
			}
		}

		if (entityId == null) {
			throw new MissingAnnotationException("Id");
		}

		// -----------------------------
		String query = "SELECT * FROM "
				+ StringUtils.camelCaseToUnderscore(this.entityClass
						.getSimpleName()) + " WHERE "
				+ StringUtils.camelCaseToUnderscore(entityId.getName())
				+ " = (?)";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();
		List<T> results = getListOfBeansFromResultSet(rs);

		// -----------------------------

		return results.get(0);
	}

	@Override
	public List<T> list() throws Exception {
		String query = "SELECT * FROM "
				+ StringUtils.camelCaseToUnderscore(this.entityClass
						.getSimpleName().toLowerCase());
		PreparedStatement ps = this.conn.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		List<T> results = getListOfBeansFromResultSet(rs);

		return results;
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
	}

	private List<T> getListOfBeansFromResultSet(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<T>();
		ResultSetMetaData rsmd = rs.getMetaData();

		if (rs != null) {
			while (rs.next()) {
				T bean = (T) this.entityClass.newInstance();
				for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
					String columnName = StringUtils.underscoreToCamelCase(rsmd.getColumnName(_iterator + 1));
					Object columnValue = rs.getObject(_iterator + 1);

					for (Field field : getEntityDeclaredFields()) {
						if (field.getName().equalsIgnoreCase(columnName)
								&& columnValue != null) {
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

	private Field[] getEntityDeclaredFields() {
		return this.entityClass.getDeclaredFields();
	}

}
