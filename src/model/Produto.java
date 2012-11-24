package model;

public class Produto extends Entity {
	private String	descricao;
	private Integer	quantidade;
	private Double	valor;

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
