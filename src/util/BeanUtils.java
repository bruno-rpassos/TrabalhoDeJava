package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Entity;

public class BeanUtils {
	@SuppressWarnings( "rawtypes" )
	public static Field[] getDeclaredFieldsFromClassAndSuperclass( final Class clazz ) {
		final List<Field> classFields = new ArrayList<Field>( Arrays.asList( clazz.getDeclaredFields() ) );
		final List<Field> superclassFields = new ArrayList<Field>( Arrays.asList( clazz.getSuperclass().getDeclaredFields() ) );
		classFields.addAll( superclassFields );
		return classFields.toArray( new Field[classFields.size()] );
	}

	@SuppressWarnings( "rawtypes" )
	public static Field getFieldFromClassOrSuperclass( final Class beanClass, final String field ) throws NoSuchFieldException {
		Field found = null;
		for ( final Field f : BeanUtils.getDeclaredFieldsFromClassAndSuperclass( beanClass ) )
			if ( f.getName().equals( field ) ) found = f;
		if ( found == null ) throw new NoSuchFieldException();
		return found;
	}

	public static void setProperty( final Entity bean, final String field, Object value ) {
		try {
			final Field f = BeanUtils.getFieldFromClassOrSuperclass( bean.getClass(), field );
			f.setAccessible( true );
			if ( f.getType() == Integer.class ) value = Integer.valueOf( value.toString() );
			else if ( f.getType() == Double.class ) value = Double.valueOf( value.toString() );
			f.set( bean, value );
			f.setAccessible( false );
		} catch ( final NoSuchFieldException e ) {
			e.printStackTrace();
		} catch ( final SecurityException e ) {
			e.printStackTrace();
		} catch ( final IllegalArgumentException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}
}
