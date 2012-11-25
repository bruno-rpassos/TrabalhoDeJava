package exception;

@SuppressWarnings( "serial" )
public class PessoaNotFoundException extends DefaultException {
	public PessoaNotFoundException() {
		super( "PESSOA nao encontrado!" );
	}
}
