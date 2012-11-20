package view.vo;

import java.math.BigDecimal;

public class ProdutoVO {
	private Integer id;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	
	public static final int ID = 0;
	public static final int DESCRICAO = 1;
	public static final int QUANTIDADE = 2;
	public static final int VALOR = 3;

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
