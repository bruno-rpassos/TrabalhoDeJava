package repository;

import java.util.HashMap;
import java.util.Map;

import model.User;
import exception.PassNotFoundException;

public class PasswordRepository {

	private static PasswordRepository	instance;
	private Map<User, String>			passs;

	private PasswordRepository() {
		passs = new HashMap<User, String>();
	}

	public static PasswordRepository getInstance() {
		if (instance == null)
			instance = new PasswordRepository();
		return instance;
	}

	public void validatePass(User u, String p) throws PassNotFoundException {
		String existingPass = this.passs.get(u);

		if (!existingPass.equals(p))
			throw new PassNotFoundException();
	}

	public void addNewPassWithUser(String p, User u) {
		this.passs.put(u, p);
	}

}
