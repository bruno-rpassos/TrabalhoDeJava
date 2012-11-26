package dao;

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
		return super.getByField("tipo", tipo.toString()).get(0);
	}

}
