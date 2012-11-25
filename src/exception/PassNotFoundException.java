package exception;

@SuppressWarnings( "serial" )
public class PassNotFoundException extends DefaultException {
	public PassNotFoundException() {
		super( "PASSWORD invalido!" );
	}
}
