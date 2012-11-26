package controller;

import java.util.List;

import javax.swing.JDialog;

import model.User;
import repository.UserRepository;
import dao.UserDAO;
import exception.NotImplementedYet;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

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
		NotImplementedYet.NOT_IMPLEMENTED_YET();
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
