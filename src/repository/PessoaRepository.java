package repository;

import java.util.ArrayList;
import java.util.List;

import model.Pessoa;
import exception.PessoaNotFoundException;

public class PessoaRepository {
	private static Integer			ID	= 1;
	private static PessoaRepository	instance;

	public static PessoaRepository getInstance() {
		if ( PessoaRepository.instance == null ) {
			PessoaRepository.instance = new PessoaRepository();
		}
		return PessoaRepository.instance;
	}

	private final List<Pessoa>	pessoas;

	private PessoaRepository() {
		this.pessoas = new ArrayList<Pessoa>();
	}

	public void add( final Pessoa pessoa ) throws PessoaNotFoundException {
		if ( pessoa.getId() == null ) {
			pessoa.setId( new Integer( PessoaRepository.ID++ ) );
		} else {
			final Pessoa existente = this.getById( pessoa.getId() );
			if ( existente != null ) {
				this.pessoas.remove( existente );
			}
		}

		this.pessoas.add( pessoa );
	}

	public List<Pessoa> getAll() {
		return this.pessoas;
	}

	public Pessoa getById( final Integer id ) throws PessoaNotFoundException {
		for ( final Pessoa p : this.pessoas ) {
			if ( p.getId().equals( id ) ) return p;
		}
		throw new PessoaNotFoundException();
	}

}
