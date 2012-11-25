package exception;

@SuppressWarnings( "serial" )
public class VendaNotFoundException extends DefaultException {
	public VendaNotFoundException() {
		super( "Venda nao encontrada" );
	}
}
