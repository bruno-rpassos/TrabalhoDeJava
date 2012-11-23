package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.User;
import repository.UserRepository;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

public class UserDAO implements DAO<User> {
	private static UserDAO instance;
	private List<Listener> listeners;

	private UserDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	@Override
	public void saveOrUpdate(User user) throws Exception {
		UserRepository.getInstance().add(user);
		dataChanged();
	}

	@Override
	public User getById(Integer id) throws Exception {
		return UserRepository.getInstance().getById(id);
	}

	public User getByLogin(String login) throws UserNotFoundException,
			PassNotFoundException {
		User user = UserRepository.getInstance().getUser(login);

		return user;
	}

	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public void addListener(Listener l) {

	}

	private void dataChanged() {
		for (Listener l : this.listeners) {
			l.actionPerformed();
		}
	}
}
