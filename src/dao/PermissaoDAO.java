package dao;

import java.util.List;

import exception.MissingAnnotationException;
import exception.PermissaoNotFoundException;
import exception.SQLException;

import model.Permissao;

public class PermissaoDAO extends AbstractDAO<Permissao> {
	private static PermissaoDAO	instance;


	private PermissaoDAO() throws MissingAnnotationException, ClassNotFoundException, SQLException {
		super(Permissao.class);
	}
	
	public static PermissaoDAO getInstance() throws SQLException {
		if ( PermissaoDAO.instance == null ) {
			try {
				PermissaoDAO.instance = new PermissaoDAO();
			} catch (MissingAnnotationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return PermissaoDAO.instance;
	}

	public Permissao getByType( final Integer tipo ) throws Exception {
		List<Permissao> permissoes = super.getByField("tipo", tipo);
		if(permissoes.size() == 0) throw new PermissaoNotFoundException();
		return permissoes.get(0);
	}

}
