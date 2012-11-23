package repository;

import java.util.ArrayList;
import java.util.List;

import model.User;
import exception.UserNotFoundException;

public class UserRepository {

	private static UserRepository instance;
	private List<User> users;

	private UserRepository() {
		users = new ArrayList<User>();
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

	public List<User> getAll() {
		return this.users;
	}

}
