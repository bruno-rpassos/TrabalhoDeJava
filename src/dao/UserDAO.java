package dao;

import java.util.List;

import dao.repository.PasswordRepository;
import dao.repository.UserRepository;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

import model.entity.User;
import model.listener.Listener;

public class UserDAO implements DAO<User> {
	private static UserDAO instance;
	
	private UserDAO() {}
	
	public static UserDAO getInstance() {
		if(instance == null)
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
	
	public User getByLoginAndPassword(String login, String password) throws UserNotFoundException, PassNotFoundException {
		User user = UserRepository.getInstance().getUser(login);
		PasswordRepository.getInstance().validatePass(user, password);
		
		return user;
	}

	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public void addListener(Listener l) {

	}

}
