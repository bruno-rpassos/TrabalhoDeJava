package controller;

import java.util.ArrayList;
import java.util.List;

import model.Permissao;
import model.User;
import dao.PermissaoDAO;
import exception.NotImplementedYet;
import exception.TypeNotFoundException;

public class PermissaoController implements Controller<Permissao> {

	private static PermissaoController	instance;

	public static PermissaoController getInstance() {
		if ( PermissaoController.instance == null ) PermissaoController.instance = new PermissaoController();
		return PermissaoController.instance;
	}

	private PermissaoController() {}

	@Override
	public void create( final Permissao p ) {
		try {
			PermissaoDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit( final Integer id ) throws TypeNotFoundException {

	}

	@Override
	public Permissao get( final Integer id ) {
		Permissao p = null;

		try {
			p = PermissaoDAO.getInstance().getByType( id );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public List<Permissao> getAll() {
		List<Permissao> lista = new ArrayList<Permissao>();
		try {
			lista = PermissaoDAO.getInstance().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean isAdmin( final User user ) {
		return this.isPermitido( user, Permissao.ADMIN );
	}

	public boolean isVendedor( final User user ) {
		return this.isPermitido( user, Permissao.VENDEDOR );
	}

	@Override
	public void list() {
		NotImplementedYet.NOT_IMPLEMENTED_YET();
	}

	@Override
	public void newResource() {
		NotImplementedYet.NOT_IMPLEMENTED_YET();
	}

	@Override
	public void update( final Permissao p ) {
		try {
			PermissaoDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	private boolean isPermitido( final User user, final Integer permissao ) {
		return user.getPermissao().getTipo().equals( permissao );
	}
}
