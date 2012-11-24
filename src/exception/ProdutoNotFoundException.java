package exception;

@SuppressWarnings( "serial" )
public class ProdutoNotFoundException extends Exception {
	public ProdutoNotFoundException() {
		super( "Produto nao encontrado" );
	}
}
