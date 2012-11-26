package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Produto;

public class ProdutoDAO extends AbstractDAO<Produto> {
	private static ProdutoDAO	instance;

	public static ProdutoDAO getInstance() throws Exception {
		if ( ProdutoDAO.instance == null ) {
			ProdutoDAO.instance = new ProdutoDAO();
		}
		return ProdutoDAO.instance;
	}

	private final List<Listener>	listeners;

	private ProdutoDAO() throws Exception {
		super(Produto.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public void saveOrUpdate( final Produto produto ) throws Exception {
		super.saveOrUpdate(produto);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
