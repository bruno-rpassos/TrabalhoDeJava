package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Pessoa;
import exception.MissingAnnotationException;
import exception.SQLException;

public class PessoaDAO extends AbstractDAO<Pessoa> {
	private static PessoaDAO	instance;

	public static PessoaDAO getInstance() throws SQLException {
		if ( PessoaDAO.instance == null ) {
			try {
				PessoaDAO.instance = new PessoaDAO();
			} catch (MissingAnnotationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return PessoaDAO.instance;
	}

	private final List<Listener>	listeners;

	private PessoaDAO() throws SQLException, MissingAnnotationException, ClassNotFoundException {
		super(Pessoa.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public void saveOrUpdate( final Pessoa pessoa ) throws SQLException {
		super.saveOrUpdate(pessoa);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
