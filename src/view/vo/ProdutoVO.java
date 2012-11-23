package view.vo;

import annotation.Input;

public class ProdutoVO extends VO {

	@Input(label = "Descrição", name = "descricao")
	private String descricao;

	@Input(label = "Valor", name = "valor")
	private Double valor;

	@Input(label = "Quantidade", name = "quantidade")
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
