package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Produto;
import exception.MissingAnnotationException;
import exception.SQLException;

public class ProdutoDAO extends AbstractDAO<Produto> {
	private static ProdutoDAO	instance;

	public static ProdutoDAO getInstance() throws SQLException {
		if ( ProdutoDAO.instance == null ) {
			try {
				ProdutoDAO.instance = new ProdutoDAO();
			} catch (MissingAnnotationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ProdutoDAO.instance;
	}

	private final List<Listener>	listeners;

	private ProdutoDAO() throws MissingAnnotationException, ClassNotFoundException, SQLException {
		super(Produto.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public void saveOrUpdate( final Produto produto ) throws SQLException {
		super.saveOrUpdate(produto);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
