package controller.login;

import model.entity.User;
import dao.repository.PasswordRepository;

public class PasswordController {

	public static void addPassForUser(String p, User u) {
		PasswordRepository.getInstance().addNewPassWithUser(p, u);
	}
}
