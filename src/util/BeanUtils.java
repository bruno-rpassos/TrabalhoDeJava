package util;

import java.lang.reflect.Field;

import model.Entity;

public class BeanUtils {
	public static void setProperty( final Entity bean, final String field, final Object value ) {
		try {
			final Field f = bean.getClass().getDeclaredField( field );
			f.setAccessible( true );
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
