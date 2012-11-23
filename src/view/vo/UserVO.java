package view.vo;

import annotation.Input;

public class UserVO extends VO {

	@Input(label = "Nome")
	private String nome;

	@Input(label = "Senha")
	private String senha;

	public static final int ID = 0;
	public static final int NOME = 1;

	public UserVO() {
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
