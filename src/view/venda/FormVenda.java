package view.venda;

import model.Venda;
import view.Form;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class FormVenda extends Form<Venda> {

	public FormVenda() throws TypeNotFoundException {
		super( Venda.class, "VENDA" );

	}
}
