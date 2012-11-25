package controller;

import java.util.List;

import javax.swing.JDialog;

import model.User;
import repository.UserRepository;
import dao.UserDAO;
import exception.PassNotFoundException;
import exception.TypeNotFoundException;
import exception.UserNotFoundException;

public class UserController implements Controller<User> {

	private static UserController	instance;

	public static UserController getInstance() {
		if ( UserController.instance == null ) {
			UserController.instance = new UserController();
		}
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
		try {
			final User user = UserDAO.getInstance().getById( id );
			final JDialog view = new view.usuario.EditUser( user );
			view.setVisible( true );
		} catch ( final UserNotFoundException e ) {} catch ( final TypeNotFoundException e ) {}
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
		return UserDAO.getInstance().list();
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
		try {
			final JDialog view = new view.usuario.ListaUser();
			view.setVisible( true );
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void newResource() {
		try {
			final JDialog view = new view.usuario.NewUser();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update( final User u ) {
		UserDAO.getInstance().saveOrUpdate( u );
	}

	public void validarLogin( final String user, final String pass ) throws PassNotFoundException, UserNotFoundException {
		final User u = UserRepository.getInstance().getUser( user );

		if ( !u.getSenha().equals( pass ) ) throw new PassNotFoundException();
	}

}
