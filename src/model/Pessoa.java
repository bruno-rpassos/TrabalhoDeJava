package model;

import annotation.Entity;
import annotation.Input;
import annotation.Transient;

@Entity
public class Pessoa extends model.Entity {
	
	@Transient
	public static final int	CIDADE		= 4;
	@Transient
	public static final int	CPF			= 2;
	@Transient
	public static final int	ENDERECO	= 3;
	@Transient
	public static final int	ID			= 0;
	@Transient
	public static final int	LIMITE		= 5;
	@Transient
	public static final int	NOME		= 1;

	@Input( label = "Cidade / Estado", name = "cidadeestado" )
	private String			cidadeEstado;
	
	@Input( label = "CPF", name = "cpf" )
	private String			cpf;

	@Input( label = "Locadouro", name = "endereco" )
	private String			endereco;

	@Input( label = "Limite de Crédito", name = "limitedecredito" )
	private Double			limiteCredito;

	@Input( label = "Nome", name = "nome" )
	private String			nome;

	public String getCidadeEstado() {
		return this.cidadeEstado;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public Double getLimiteCredito() {
		return this.limiteCredito;
	}

	public String getNome() {
		return this.nome;
	}

	public void setCidadeEstado( final String cidadeEstado ) {
		this.cidadeEstado = cidadeEstado;
	}

	public void setCpf( final String cpf ) {
		this.cpf = cpf;
	}

	public void setEndereco( final String endereco ) {
		this.endereco = endereco;
	}

	public void setLimiteCredito( final Double limiteCredito ) {
		this.limiteCredito = limiteCredito;
	}

	public void setNome( final String nome ) {
		this.nome = nome;
	}
}
