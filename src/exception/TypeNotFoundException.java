package exception;

@SuppressWarnings( "serial" )
public class TypeNotFoundException extends DefaultException {
	public TypeNotFoundException() {
		super( "TIPO NÃO ENCONTRADO" );
	}
}
