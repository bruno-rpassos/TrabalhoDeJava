package dao;

import java.util.List;

import model.Listener;
import model.User;

public class UserDAO implements DAO<User> {
	public static UserDAO instance;
	
	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	@Override
	public void saveOrUpdate(User obj) throws Exception {
	}

	@Override
	public User getById(Integer id) throws Exception {
		return null;
	}

	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public void addListener(Listener l) {

	}
}
