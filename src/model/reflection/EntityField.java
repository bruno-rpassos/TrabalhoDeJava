package model.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityField {
	public static List<EntityField> getListByArray( final Field[] fields ) {
		final List<EntityField> lista = new ArrayList<EntityField>();

		for ( final Field f : fields )
			lista.add( new EntityField( f ) );

		return lista;
	}

	private final Field	field;

	public EntityField( final Field field ) {
		this.field = field;
	}

	public Object get( final Object obj ) throws IllegalArgumentException, IllegalAccessException {
		this.field.setAccessible( true );
		final Object value = this.field.get( obj );
		this.field.setAccessible( false );
		return value;
	}

	public String getJoinTableName() {
		return this.field.getAnnotation( annotation.JoinTable.class ).name();
	}

	public String getName() {
		this.field.setAccessible( true );
		final String name = this.field.getName();
		this.field.setAccessible( false );
		return name;
	}

	public Boolean isId() {
		return this.field.getName() == "id";
	}

	public Boolean isManyToMany() {
		return this.field.isAnnotationPresent( annotation.ManyToMany.class );
	}

	public Boolean isTransient() {
		return this.field.isAnnotationPresent( annotation.Transient.class );
	}
}
