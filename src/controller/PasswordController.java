package controller;

import repository.PasswordRepository;
import model.User;

public class PasswordController {

	public static void addPassForUser(String p, User u) {
		PasswordRepository.getInstance().addNewPassWithUser(p, u);
	}
}
