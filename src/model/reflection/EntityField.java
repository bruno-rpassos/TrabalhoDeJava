package model.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityField {
	private final Field field;
	
	public EntityField(Field field) {
		this.field = field;
	}
	
	public static List<EntityField> getListByArray(Field[] fields) {
		List<EntityField> lista = new ArrayList<EntityField>();
		
		for(Field f : fields) {
			lista.add(new EntityField(f));
		}
		
		return lista;
	}
	
	public Boolean isTransient() {
		return this.field.isAnnotationPresent(annotation.Transient.class);
	}
	
	public Boolean isManyToMany() {
		return this.field.isAnnotationPresent(annotation.ManyToMany.class);
	}
	
	public Boolean isId() {
		return this.field.getName() == "id";
	}

	public String getName() {
		this.field.setAccessible(true);
		String name = this.field.getName();
		this.field.setAccessible(false);
		return name;
	}
	
	public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException {
		this.field.setAccessible(true);
		Object value = this.field.get(obj); 
		this.field.setAccessible(false);
		return value;
	}
	
	public String getJoinTableName() {
		return field.getAnnotation(annotation.JoinTable.class).name();
	}
}
