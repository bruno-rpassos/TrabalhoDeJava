package model;

import annotation.Id;
import annotation.Input;
import annotation.Entity;

@Entity
public class Produto extends model.Entity {
	public static final int	DESCRICAO	= 1;
	public static final int	ID			= 0;
	public static final int	QUANTIDADE	= 2;
	public static final int	VALOR		= 3;

	@Id
	private Integer id;

	@Input( label = "Descricao", name = "descricao" )
	private String			descricao;

	@Input( label = "Quantidade", name = "quantidade" )
	private Integer			quantidade;

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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
