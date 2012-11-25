package model;

import annotation.Persistence;

public class Permissao extends Entity {

	public static final Integer	ADMIN		= 0;
	public static final Integer	VENDEDOR	= 1;
	@Persistence
	private final Integer		tipo;

	public Permissao( final Integer tipo ) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return this.tipo;
	}
}
