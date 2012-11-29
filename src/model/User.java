package model;

import annotation.BelongsTo;
import annotation.Entity;
import annotation.Input;
import annotation.Table;
import annotation.Transient;

@Entity
@Table(name = "usuario")
public class User extends model.Entity {
	
	@Transient
	public static final int	ID		= 0;
	@Transient
	public static final int	NOME	= 1;
	@Input( label = "Nome", name = "nome" )
	private String			nome;
	
	@BelongsTo
	private Permissao		permissao;

	@Input( label = "Senha", name = "senha", type = "PASSWORD" )
	private String			senha;

	public User() {}

	public String getNome() {
		return this.nome;
	}

	public Permissao getPermissao() {
		return this.permissao;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setNome( final String nome ) {
		this.nome = nome;
	}

	public void setPermissao( final Permissao p ) {
		this.permissao = p;
	}

	public void setSenha( final String senha ) {
		this.senha = senha;
	}

}
