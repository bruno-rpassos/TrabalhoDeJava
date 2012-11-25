package view.venda;

import model.Venda;
import view.tablemodel.ItensVendaTableModel;

@SuppressWarnings( "serial" )
public class ListaItemVenda extends ItensVendaTableModel {
	public ListaItemVenda( final Venda venda ) throws InstantiationException, IllegalAccessException {
		super( venda );
	}
}
