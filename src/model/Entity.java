package model;

import annotation.Id;

public abstract class Entity {
	@Id
	protected Integer	id;

	public Integer getId() {
		return this.id;
	}

	public void setId( final Integer id ) {
		this.id = id;
	}

}
