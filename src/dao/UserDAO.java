package dao;

import java.util.List;

import model.entity.User;
import model.listener.Listener;

public class UserDAO implements DAO<User> {

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
