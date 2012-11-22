package exception;

@SuppressWarnings("serial")
public class PassNotFoundException extends Exception {
	public PassNotFoundException() {
		super("PASSWORD invalido!");
	}
}
