package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;
import dao.UserDAO;
import exception.NotImplementedYet;
import exception.PassNotFoundException;

public class UserController implements Controller<User> {

	private static UserController	instance;

	public static UserController getInstance() {
		if ( UserController.instance == null ) UserController.instance = new UserController();
		return UserController.instance;
	}

	@Override
	public void create( final User u ) {
		try {
			UserDAO.getInstance().saveOrUpdate( u );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit( final Integer id ) {
		NotImplementedYet.NOT_IMPLEMENTED_YET();
	}

	@Override
	public User get( final Integer id ) {
		User u = null;

		try {
			u = UserDAO.getInstance().getById( id );
		} catch ( final Exception e ) {}

		return u;
	}

	@Override
	public List<User> getAll() {
		List<User> lista = new ArrayList<User>();
		try {
			lista = UserDAO.getInstance().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public User getByName( final String name ) {
		User u = null;

		try {
			u = UserDAO.getInstance().getByLogin( name );
		} catch ( final Exception e ) {}

		return u;
	}

	@Override
	public void list() {
		NotImplementedYet.NOT_IMPLEMENTED_YET();
	}

	@Override
	public void newResource() {
		try {
			new view.usuario.NewUser();
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update( final User u ) {
		try {
			UserDAO.getInstance().saveOrUpdate( u );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validarLogin( final String user, final String pass ) throws Exception {
		final User u =  UserDAO.getInstance().getByLogin( user );
		if ( !u.getSenha().equals( pass ) ) throw new PassNotFoundException();
		Sessao.getInstance().setLogado( u );
	}

}
