package dao;

import java.util.List;

import model.Permissao;
import exception.MissingAnnotationException;
import exception.PermissaoNotFoundException;
import exception.SQLException;

public class PermissaoDAO extends AbstractDAO<Permissao> {
	private static PermissaoDAO	instance;

	public static PermissaoDAO getInstance() throws SQLException {
		if ( PermissaoDAO.instance == null ) try {
			PermissaoDAO.instance = new PermissaoDAO();
		} catch ( final MissingAnnotationException e ) {
			e.printStackTrace();
		} catch ( final ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return PermissaoDAO.instance;
	}

	private PermissaoDAO() throws MissingAnnotationException, ClassNotFoundException, SQLException {
		super( Permissao.class );
	}

	public Permissao getByType( final Integer tipo ) throws Exception {
		final List<Permissao> permissoes = super.getByField( "tipo", tipo );
		if ( permissoes.size() == 0 ) throw new PermissaoNotFoundException();
		return permissoes.get( 0 );
	}

}
