package view.pessoa;

import model.Pessoa;
import view.Form;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class FormPessoa extends Form<Pessoa> {
	public FormPessoa() throws TypeNotFoundException {
		super( Pessoa.class, "PRODUTO" );
	}
}
