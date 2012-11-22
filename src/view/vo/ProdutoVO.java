package view.vo;

import java.math.BigDecimal;

import annotation.Input;

public class ProdutoVO implements VO {
	

	@Input(label="Descrição")
	private String			descricao;
	
	@Input(label="Valor")
	private BigDecimal		valor;
	
	@Input(label="Quantidade")
	private Integer			quantidade;
	
	private Integer			id;

	public static final int	ID			= 0;
	public static final int	DESCRICAO	= 1;
	public static final int	QUANTIDADE	= 2;
	public static final int	VALOR		= 3;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
