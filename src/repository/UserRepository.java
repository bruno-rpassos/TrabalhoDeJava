package repository;

import java.util.ArrayList;
import java.util.List;

import model.User;
import exception.UserNotFoundException;

public class UserRepository {

	private static UserRepository	instance;

	public static UserRepository getInstance() {
		if ( UserRepository.instance == null ) {
			UserRepository.instance = new UserRepository();
		}
		return UserRepository.instance;
	}

	private final List<User>	users;

	private UserRepository() {
		this.users = new ArrayList<User>();
	}

	public void add( final User u ) {
		this.users.add( u );
	}

	public List<User> getAll() {
		return this.users;
	}

	public User getById( final Integer id ) throws UserNotFoundException {
		for ( final User u : this.users ) {
			if ( u.getId().equals( id ) ) return u;
		}
		throw new UserNotFoundException();
	}

	public User getUser( final String name ) throws UserNotFoundException {
		for ( final User u : this.users ) {
			if ( u.getNome().equals( name ) ) return u;
		}

		throw new UserNotFoundException();
	}

}
