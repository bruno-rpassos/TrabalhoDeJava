package exception;

@SuppressWarnings( "serial" )
public class ProdutoNotFoundException extends DefaultException {
	public ProdutoNotFoundException() {
		super( "Produto nao encontrado" );
	}
}
