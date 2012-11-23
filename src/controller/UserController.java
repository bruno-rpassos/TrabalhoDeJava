package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.User;
import repository.UserRepository;
import view.vo.UserVO;
import dao.UserDAO;
import exception.PassNotFoundException;
import exception.UserNotFoundException;
import factory.UserFactory;

public class UserController {

	public static void validarLogin(String user, String pass)
			throws PassNotFoundException, UserNotFoundException {
		User u = UserRepository.getInstance().getUser(user);
		if (!u.getSenha().equals(pass))
			throw new PassNotFoundException();
	}

	public static void registerNewUser(User u) {
		UserRepository.getInstance().add(u);
	}

	public static List<UserVO> getAllUserVO() {
		List<User> users = UserDAO.getInstance().list();
		List<UserVO> vo = new ArrayList<UserVO>();

		for (User user : users) {
			vo.add(UserFactory.beanToVO(user));
		}

		return vo;
	}

	public static void create(UserVO vo) {
		User user = UserFactory.getUserByVO(vo);
		try {
			UserDAO.getInstance().saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update(UserVO vo) {
		User user = UserFactory.getUserByVO(vo);
		try {
			UserDAO.getInstance().saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void newResource() {
		try {
			JDialog view = new view.usuario.NewUser();
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
