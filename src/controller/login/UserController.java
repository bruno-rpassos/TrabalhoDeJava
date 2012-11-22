package controller.login;

import model.entity.User;
import dao.repository.PasswordRepository;
import dao.repository.UserRepository;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

public class UserController {

	public static void validarLogin(String user, String pass) throws PassNotFoundException, UserNotFoundException {
		User u = UserRepository.getInstance().getUser(user);
		PasswordRepository.getInstance().validatePass(u, pass);
	}

	public static void registerNewUserWithPassword(User u, String p) {
		UserRepository.getInstance().addNewUser(u);
		PasswordController.addPassForUser(p, u);
	}

}
