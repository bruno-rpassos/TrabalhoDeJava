package exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("USER nao encontrado!");
	}
}
