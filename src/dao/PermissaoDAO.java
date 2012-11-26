package dao;

import java.util.List;

import exception.PermissaoNotFoundException;

import model.Permissao;

public class PermissaoDAO extends AbstractDAO<Permissao> {
	private static PermissaoDAO	instance;


	private PermissaoDAO() throws Exception {
		super(Permissao.class);
	}
	
	public static PermissaoDAO getInstance() throws Exception {
		if ( PermissaoDAO.instance == null ) {
			PermissaoDAO.instance = new PermissaoDAO();
		}
		return PermissaoDAO.instance;
	}

	public Permissao getByType( final Integer tipo ) throws Exception {
		List<Permissao> permissoes = super.getByField("tipo", tipo);
		if(permissoes.size() == 0) throw new PermissaoNotFoundException();
		return permissoes.get(0);
	}

}
