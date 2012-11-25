package exception;

@SuppressWarnings( "serial" )
public class PermissaoNegadaException extends Exception {
	public PermissaoNegadaException( final String message ) {
		super( "O USUARIO NAO TEM PERMISSAO PARA " + message.toUpperCase() );
	}
}
