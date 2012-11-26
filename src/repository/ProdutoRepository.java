package repository;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import exception.ProdutoInvalidException;
import exception.ProdutoNotFoundException;

public class ProdutoRepository {
	private static Integer				ID	= 1;
	private static ProdutoRepository	instance;

	public static ProdutoRepository getInstance() {
		if ( ProdutoRepository.instance == null ) ProdutoRepository.instance = new ProdutoRepository();
		return ProdutoRepository.instance;
	}

	private final List<Produto>	produtos;

	private ProdutoRepository() {
		this.produtos = new ArrayList<Produto>();
	}

	public void add( final Produto produto ) throws ProdutoNotFoundException, ProdutoInvalidException {
		if ( produto.getId() == null ) produto.setId( new Integer( ProdutoRepository.ID++ ) );
		else {
			final Produto existente = this.getById( produto.getId() );
			if ( existente != null ) this.produtos.remove( existente );
		}

		this.produtos.add( produto );
	}

	public List<Produto> getAll() {
		return this.produtos;
	}

	public Produto getById( final Integer id ) throws ProdutoNotFoundException, ProdutoInvalidException {
		for ( final Produto p : this.produtos )
			if ( p.getId().equals( id ) ) return p;
		throw new ProdutoNotFoundException();
	}

}
