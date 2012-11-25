package exception;

@SuppressWarnings( "serial" )
public class UserNotFoundException extends DefaultException {
	public UserNotFoundException() {
		super( "USER nao encontrado!" );
	}
}
