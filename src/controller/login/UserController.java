package controller.login;

import model.entity.User;
import dao.UserDAO;
import dao.repository.Sessao;
import dao.repository.UserRepository;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

public class UserController {

	public static void validarLogin(String user, String pass) throws PassNotFoundException, UserNotFoundException {
		User logado = UserDAO.getInstance().getByLoginAndPassword(user, pass);
		Sessao.getInstance().setLogado(logado);
	}

	public static void registerNewUserWithPassword(User u, String p) {
		UserRepository.getInstance().addNewUser(u);
		PasswordController.addPassForUser(p, u);
	}

}
