package repository;

import java.util.HashSet;
import java.util.Set;

import model.User;
import exception.UserNotFoundException;

public class UserRepository {

	private static UserRepository instance;
	private Set<User> users;

	private UserRepository() {
		users = new HashSet<User>();
	}

	public static UserRepository getInstance() {
		if (instance == null)
			instance = new UserRepository();
		return instance;
	}

	public User getUser(String name) throws UserNotFoundException {
		for (User u : this.users) {
			if (u.getNome().equals(name))
				return u;
		}

		throw new UserNotFoundException();
	}

	public void add(User u) {
		this.users.add(u);
	}

	public User getById(Integer id) throws UserNotFoundException {
		for (User u : this.users) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		throw new UserNotFoundException();
	}

}
