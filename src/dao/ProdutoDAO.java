package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Produto;
import repository.ProdutoRepository;
import exception.ProdutoInvalidException;
import exception.ProdutoNotFoundException;

public class ProdutoDAO implements DAO<Produto> {
	private static ProdutoDAO	instance;

	public static ProdutoDAO getInstance() {
		if ( ProdutoDAO.instance == null ) {
			ProdutoDAO.instance = new ProdutoDAO();
		}
		return ProdutoDAO.instance;
	}

	private final List<Listener>	listeners;

	private ProdutoDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public Produto getById( final Integer id ) throws ProdutoInvalidException, ProdutoNotFoundException {
		return ProdutoRepository.getInstance().getById( id );
	}

	@Override
	public List<Produto> list() {
		return ProdutoRepository.getInstance().getAll();
	}

	@Override
	public void saveOrUpdate( final Produto produto ) throws Exception {
		produto.setDescricao( produto.getDescricao().toUpperCase() );
		ProdutoRepository.getInstance().add( produto );
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners ) {
			l.actionPerformed();
		}
	}

}
