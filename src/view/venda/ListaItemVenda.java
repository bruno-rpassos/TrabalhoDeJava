package view.venda;

import view.tablemodel.ItensVendaTableModel;
import view.tablemodel.ProdutosTableModel;
import controller.ProdutosController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaItemVenda extends view.Lista<ItensVendaTableModel> {
	public ListaItemVenda() throws InstantiationException, IllegalAccessException {
		super( ProdutosTableModel.class );
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException {
		final Integer id = ( Integer ) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		// Produto p =
		// FIXME implementar busca produto na venda ao doubleclick
		System.out.println( "looking for produto with id " + id );
		ProdutosController.getInstance().edit( id );
	}
}
