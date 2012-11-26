package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.User;
import repository.UserRepository;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

public class UserDAO implements DAO<User> {
	private static UserDAO	instance;

	public static UserDAO getInstance() {
		if ( UserDAO.instance == null ) UserDAO.instance = new UserDAO();
		return UserDAO.instance;
	}

	private final List<Listener>	listeners;

	private UserDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public User getById( final Integer id ) throws UserNotFoundException {
		return UserRepository.getInstance().getById( id );
	}

	public User getByLogin( final String login ) throws UserNotFoundException, PassNotFoundException {
		final User user = UserRepository.getInstance().getUser( login );

		return user;
	}

	@Override
	public List<User> list() {
		return UserRepository.getInstance().getAll();
	}

	@Override
	public void saveOrUpdate( final User user ) {
		UserRepository.getInstance().add( user );
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}
}
