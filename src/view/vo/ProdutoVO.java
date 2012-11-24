package view.vo;

import annotation.Persistence;

public class ProdutoVO extends VO {

	public static final int	DESCRICAO	= 1;

	public static final int	ID			= 0;

	public static final int	QUANTIDADE	= 2;

	public static final int	VALOR		= 3;
	@Persistence( label = "Descrição", name = "descricao" )
	private String			descricao;
	@Persistence( label = "Quantidade", name = "quantidade" )
	private Integer			quantidade;
	@Persistence( label = "Valor", name = "valor" )
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
