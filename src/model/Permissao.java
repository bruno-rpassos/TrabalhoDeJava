package model;

import annotation.Entity;
import annotation.Transient;

@Entity
public class Permissao extends model.Entity {

	@Transient
	public static final Integer	ADMIN		= 9;
	@Transient
	public static final Integer	VENDEDOR	= 15;

	private Integer		tipo;
	
	public Permissao() {
		this.tipo = VENDEDOR;
	}

	public Permissao( final Integer tipo ) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return this.tipo;
	}

}
