package model;

import annotation.Input;
import annotation.Persistence;

public class Produto extends Entity {
	public static final int	DESCRICAO	= 1;
	public static final int	ID			= 0;
	public static final int	QUANTIDADE	= 3;
	public static final int	VALOR		= 4;

	@Persistence
	@Input( label = "Descricao", name = "descricao" )
	private String			descricao;

	@Persistence
	@Input( label = "Quantidade", name = "quantidade" )
	private Integer			quantidade;

	@Persistence
	@Input( label = "Valor R$", name = "valor" )
	private Double			valor;

	public String getDescricao() {
		return this.descricao;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setDescricao( final String descricao ) {
		this.descricao = descricao;
	}

	public void setQuantidade( final Integer quantidade ) {
		this.quantidade = quantidade;
	}

	public void setValor( final Double valor ) {
		this.valor = valor;
	}
}
