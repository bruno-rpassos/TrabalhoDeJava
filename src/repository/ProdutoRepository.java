package repository;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import exception.ProdutoNotFoundException;

public class ProdutoRepository {
	private static Integer				id	= 1;
	private static ProdutoRepository	repo;

	public static ProdutoRepository getInstance() {
		if ( ProdutoRepository.repo == null ) {
			ProdutoRepository.repo = new ProdutoRepository();
		}
		return ProdutoRepository.repo;
	}

	private final List<Produto>	produtos;

	private ProdutoRepository() {
		this.produtos = new ArrayList<Produto>();
	}

	public void add( final Produto produto ) throws Exception {
		if ( produto.getId() == null ) {
			produto.setId( new Integer( ProdutoRepository.id++ ) );
		} else {
			final Produto existente = this.getById( produto.getId() );
			if ( existente != null ) {
				this.produtos.remove( existente );
			}
		}

		this.produtos.add( produto );
	}

	public List<Produto> getAll() {
		return this.produtos;
	}

	public Produto getById( final Integer id ) throws Exception {
		for ( final Produto p : this.produtos ) {
			if ( p.getId().equals( id ) ) return p;
		}
		throw new ProdutoNotFoundException();
	}

}
