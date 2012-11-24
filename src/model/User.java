package model;

public class User extends Entity {

	private Integer	id;
	private String	nome;
	private String	senha;

	public User() {}

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSenha() {
		return this.senha;
	}

	@Override
	public void setId( final Integer id ) {
		this.id = id;
	}

	public void setNome( final String nome ) {
		this.nome = nome;
	}

	public void setSenha( final String senha ) {
		this.senha = senha;
	}

}
