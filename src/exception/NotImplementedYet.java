package exception;

@SuppressWarnings( "serial" )
public class NotImplementedYet extends Exception {
	public static void NOT_IMPLEMENTED_YET() {
		try {
			throw new NotImplementedYet();
		} catch ( final NotImplementedYet e ) {
			e.printStackTrace();
		}
	}

	public NotImplementedYet() {
		super( "METHOD NOT IMPLEMENTED" );
	}
}
