package view.produto;

import model.Produto;
import view.Form;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class FormProduto extends Form<Produto> {
	public FormProduto() throws TypeNotFoundException {
		super( Produto.class, "PRODUTO" );
	}
}
