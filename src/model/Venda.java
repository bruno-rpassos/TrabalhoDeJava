package model;

import java.util.ArrayList;
import java.util.List;

import annotation.Input;
import annotation.Persistence;
import exception.ProdutoNotFoundException;

public class Venda extends Entity {

	public static final int	DESCONTO			= 3;
	public static final int	DESCRICAO			= 1;
	public static final int	ID					= 0;
	public static final int	VALOR_COM_DESCONTO	= 4;
	public static final int	VALOR_TOTAL			= 2;

	@Persistence
	@Input( label = "Desconto %", name = "desconto" )
	private Double			desconto;

	@Persistence
	@Input( label = "Descricao", name = "descricao" )
	private String			descricao;

	@Persistence
	private List<Produto>	produtos;

	@Persistence
	private Double			valorTotal;

	public Venda() {
		this.produtos = new ArrayList<Produto>();
	}

	public void addProduto( final Produto produto ) {
		this.produtos.add( produto );
	}

	public Double getDesconto() {
		return this.desconto;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Produto getProduto( final Integer id ) throws ProdutoNotFoundException {
		for ( final Produto p : this.produtos ) {
			if ( p.getId().equals( id ) ) return p;
		}

		throw new ProdutoNotFoundException();
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setDesconto( final Double desconto ) {
		this.desconto = desconto;
	}

	public void setDescricao( final String descricao ) {
		this.descricao = descricao;
	}

	public void setProdutos( final List<Produto> produtos ) {
		this.produtos = produtos;
	}

	public void setValorTotal( final Double valorTotal ) {
		this.valorTotal = valorTotal;
	}
}
