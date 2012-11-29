package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.reflection.EntityField;
import util.BeanUtils;
import util.StringUtils;

public abstract class Entity {
	private Integer	id;

	public Map<String, Object> getAllColumnsWithValue() throws IllegalArgumentException, IllegalAccessException {
		final Map<String, Object> allColumnsWithValue = new HashMap<String, Object>();

		for ( final EntityField f : this.getEntityFields() )
			if ( !f.isTransient() && !f.isManyToMany() ) {
				if ( this.isNew() && f.isId() ) continue;

				String columnName = f.getName();
				Object value = f.get( this );

				if ( value.getClass().isAnnotationPresent( annotation.Entity.class ) ) {
					value = ( ( Entity ) value ).getId();
					columnName = columnName + "Id";
				}

				allColumnsWithValue.put( StringUtils.camelCaseToUnderscore( columnName ), value );
			}

		return allColumnsWithValue;
	}

	public Map<String, Collection<Entity>> getAllManyToManyFieldsWithValue() throws IllegalArgumentException, IllegalAccessException {
		final Map<String, Collection<Entity>> allFieldsWithValue = new HashMap<String, Collection<Entity>>();

		for ( final EntityField f : this.getEntityFields() )
			if ( f.isManyToMany() ) {
				@SuppressWarnings( "unchecked" ) final Collection<Entity> associationObjects = ( Collection<Entity> ) f.get( this );
				allFieldsWithValue.put( StringUtils.camelCaseToUnderscore( f.getName() ), associationObjects );
			}

		return allFieldsWithValue;
	}

	public Integer getId() {
		return this.id;
	}

	public Boolean isNew() {
		return this.id == null;
	}

	public void setId( final Integer id ) {
		this.id = id;
	}

	private List<EntityField> getEntityFields() {
		return EntityField.getListByArray( BeanUtils.getDeclaredFieldsFromClassAndSuperclass( this.getClass() ) );
	}

}
