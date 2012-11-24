package view.vo;

import annotation.Persistence;

public class UserVO extends VO {

	public static final int	ID		= 0;

	public static final int	NOME	= 1;

	@Persistence( label = "Nome", name = "nome" )
	private String			nome;
	@Persistence( label = "Senha", name = "senha" )
	private String			senha;

	public UserVO() {}

	public String getNome() {
		return this.nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setNome( final String nome ) {
		this.nome = nome;
	}

	public void setSenha( final String senha ) {
		this.senha = senha;
	}
}
