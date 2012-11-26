package repository;

import java.util.ArrayList;
import java.util.List;

import model.Permissao;
import exception.PermissaoNotFoundException;

public class PermissaoRepository {
	private static Integer				ID	= 1;
	private static PermissaoRepository	instance;

	public static PermissaoRepository getInstance() {
		if ( PermissaoRepository.instance == null ) PermissaoRepository.instance = new PermissaoRepository();
		return PermissaoRepository.instance;
	}

	private final List<Permissao>	permissoes;

	private PermissaoRepository() {
		this.permissoes = new ArrayList<Permissao>();
	}

	public void add( final Permissao permissao ) throws PermissaoNotFoundException {
		if ( permissao.getId() == null ) permissao.setId( new Integer( PermissaoRepository.ID++ ) );
		else {
			final Permissao existente = this.getById( permissao.getId() );
			if ( existente != null ) this.permissoes.remove( existente );
		}

		this.permissoes.add( permissao );
	}

	public List<Permissao> getAll() {
		return this.permissoes;
	}

	public Permissao getById( final Integer id ) throws PermissaoNotFoundException {
		for ( final Permissao p : this.permissoes )
			if ( p.getId().equals( id ) ) return p;
		throw new PermissaoNotFoundException();
	}

	public Permissao getByType( final Integer tipo ) throws PermissaoNotFoundException {
		for ( final Permissao p : this.permissoes )
			if ( p.getTipo().equals( tipo ) ) return p;
		throw new PermissaoNotFoundException();
	}
}
