package util;

import java.lang.reflect.Field;

import model.Entity;

public class BeanUtils {
	public static void setProperty( final Entity bean, final String field, Object value ) {
		try {
			final Field f = bean.getClass().getDeclaredField( field );
			f.setAccessible( true );
			if(f.getType() == Integer.class) {
				value = Integer.valueOf(value.toString());
			}
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
