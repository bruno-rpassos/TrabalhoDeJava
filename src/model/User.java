package model;

public class User extends Entity {
	private String	nome;
	private Integer id;

	public User(String nome) {
		this.nome = nome;
	}

	public User() {}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
