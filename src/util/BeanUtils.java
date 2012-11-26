package util;

import java.lang.reflect.Field;

import model.Entity;

public class BeanUtils {
	public static void setProperty(Entity bean, String field, Object value) {
		try {
			Field f = bean.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(bean, value);
			f.setAccessible(false);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
