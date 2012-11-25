package exception;

import javax.swing.JOptionPane;

@SuppressWarnings( "serial" )
public class DefaultException extends Exception {
	public DefaultException( final String message ) {
		super( message );
		JOptionPane.showMessageDialog( null, message );
	}
}
