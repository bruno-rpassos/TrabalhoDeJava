package model;

public class Permissao extends Entity {

	public static final Integer	ADMIN		= 9;
	public static final Integer	VENDEDOR	= 15;
	private final Integer		tipo;

	public Permissao( final Integer tipo ) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return this.tipo;
	}
}
