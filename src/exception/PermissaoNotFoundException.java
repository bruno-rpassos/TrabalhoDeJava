package exception;

@SuppressWarnings( "serial" )
public class PermissaoNotFoundException extends DefaultException {
	public PermissaoNotFoundException() {
		super( "PERMISSAO NÃO ENCONTRADA" );
	}
}
