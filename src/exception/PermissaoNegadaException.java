package exception;

@SuppressWarnings( "serial" )
public class PermissaoNegadaException extends DefaultException {
	public PermissaoNegadaException( final String message ) {
		super( "O USUARIO NAO TEM PERMISSAO PARA " + message.toUpperCase() );
	}
}
