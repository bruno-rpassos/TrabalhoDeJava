package model.entity;

public class User extends Entity {

	private final String	name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
