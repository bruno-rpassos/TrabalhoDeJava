package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Pessoa;

public class PessoaDAO extends AbstractDAO<Pessoa> {
	private static PessoaDAO	instance;

	public static PessoaDAO getInstance() throws Exception {
		if ( PessoaDAO.instance == null ) {
			PessoaDAO.instance = new PessoaDAO();
		}
		return PessoaDAO.instance;
	}

	private final List<Listener>	listeners;

	private PessoaDAO() throws Exception {
		super(Pessoa.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public void saveOrUpdate( final Pessoa pessoa ) throws Exception {
		super.saveOrUpdate(pessoa);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
