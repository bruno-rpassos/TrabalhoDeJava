package model;

import annotation.Persistence;

public abstract class Entity {
	@Persistence
	protected Integer	id;

	public Integer getId() {
		return this.id;
	}

	public void setId( final Integer id ) {
		this.id = id;
	}

}
