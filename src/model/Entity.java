package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.reflection.EntityField;
import util.BeanUtils;
import util.StringUtils;

public abstract class Entity {
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Boolean isNew() {
		return this.id == null;
	}

	public Map<String, Object> getAllColumnsWithValue()
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> allColumnsWithValue = new HashMap<String, Object>();

		for (EntityField f : getEntityFields()) {
			if (!f.isTransient() && !f.isManyToMany()) {
				if (isNew() && f.isId())
					continue;

				String columnName = f.getName();
				Object value = f.get(this);

				if (value.getClass().isAnnotationPresent(
						annotation.Entity.class)) {
					value = ((Entity) value).getId();
					columnName = columnName + "Id";
				}

				allColumnsWithValue.put(
						StringUtils.camelCaseToUnderscore(columnName), value);
			}
		}

		return allColumnsWithValue;
	}

	private List<EntityField> getEntityFields() {
		return EntityField.getListByArray(BeanUtils.getDeclaredFieldsFromClassAndSuperclass(getClass()));
	}

	public Map<String, Collection<Entity>> getAllManyToManyFieldsWithValue()
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, Collection<Entity>> allFieldsWithValue = new HashMap<String, Collection<Entity>>();

		for (EntityField f : getEntityFields()) {
			if (f.isManyToMany()) {
				@SuppressWarnings("unchecked")
				Collection<Entity> associationObjects = (Collection<Entity>) f
						.get(this);
				allFieldsWithValue.put(f.getJoinTableName(), associationObjects);
			}
		}

		return allFieldsWithValue;
	}

}
