package dao;

import java.util.List;

import model.Permissao;
import repository.PermissaoRepository;
import exception.PermissaoNotFoundException;

public class PermissaoDAO implements DAO<Permissao> {

	private static PermissaoDAO	instance;

	public static PermissaoDAO getInstance() {
		if ( PermissaoDAO.instance == null ) {
			PermissaoDAO.instance = new PermissaoDAO();
		}
		return PermissaoDAO.instance;
	}

	@Override
	public Permissao getById( final Integer id ) throws Exception {
		return PermissaoRepository.getInstance().getById( id );
	}

	public Permissao getByType( final Integer tipo ) throws Exception {
		return PermissaoRepository.getInstance().getByType( tipo );
	}

	@Override
	public List<Permissao> list() {
		return PermissaoRepository.getInstance().getAll();
	}

	@Override
	public void saveOrUpdate( final Permissao permissao ) throws PermissaoNotFoundException {
		PermissaoRepository.getInstance().add( permissao );
	}

}
