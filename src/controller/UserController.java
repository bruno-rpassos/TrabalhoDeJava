package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;
import model.UserFactory;
import repository.PasswordRepository;
import repository.UserRepository;
import view.vo.UserVO;
import dao.UserDAO;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

public class UserController {

	public static void validarLogin(String user, String pass)
			throws PassNotFoundException, UserNotFoundException {
		User u = UserRepository.getInstance().getUser(user);
		PasswordRepository.getInstance().validatePass(u, pass);
	}

	public static void registerNewUserWithPassword(User u, String p) {
		UserRepository.getInstance().addNewUser(u);
		PasswordController.addPassForUser(p, u);
	}

	public static List<UserVO> getAllUserVO() {
		List<User> users = UserDAO.getInstance().list();
		List<UserVO> vo = new ArrayList<UserVO>();

		for (User user : users) {
			vo.add(UserFactory.beanToVO(user));
		}

		return vo;
	}

	public static void update(UserVO atualizaVO) {
		// TODO Auto-generated method stub

	}

}
