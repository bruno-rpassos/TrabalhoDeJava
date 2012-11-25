package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Pessoa;
import repository.PessoaRepository;
import exception.PessoaNotFoundException;

public class PessoaDAO implements DAO<Pessoa> {
	private static PessoaDAO	instance;

	public static PessoaDAO getInstance() {
		if ( PessoaDAO.instance == null ) {
			PessoaDAO.instance = new PessoaDAO();
		}
		return PessoaDAO.instance;
	}

	private final List<Listener>	listeners;

	private PessoaDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public Pessoa getById( final Integer id ) throws PessoaNotFoundException {
		return PessoaRepository.getInstance().getById( id );
	}

	@Override
	public List<Pessoa> list() {
		return PessoaRepository.getInstance().getAll();
	}

	@Override
	public void saveOrUpdate( final Pessoa pessoa ) throws Exception {
		pessoa.setNome( pessoa.getNome().toUpperCase() );
		PessoaRepository.getInstance().add( pessoa );
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners ) {
			l.actionPerformed();
		}
	}

}
