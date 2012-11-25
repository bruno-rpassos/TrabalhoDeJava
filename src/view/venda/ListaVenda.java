package view.venda;

import view.tablemodel.VendasTableModel;
import controller.VendasController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaVenda extends view.Lista<VendasTableModel> {

	public ListaVenda() throws InstantiationException, IllegalAccessException {
		super( VendasTableModel.class );
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException {
		final Integer id = ( Integer ) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		VendasController.getInstance().edit( id );
	}
}
