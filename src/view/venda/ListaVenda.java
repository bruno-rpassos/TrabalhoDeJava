package view.venda;

import view.tablemodel.VendasTableModel;
import controller.Sessao;
import controller.VendasController;
import exception.PermissaoNegadaException;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaVenda extends view.Lista<VendasTableModel> {

	public ListaVenda() throws InstantiationException, IllegalAccessException {
		super( VendasTableModel.class );

		try {
			Sessao.getInstance().temPermissaoListarVenda();
		} catch ( final PermissaoNegadaException e1 ) {
			this.dispose();
		}
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException {
		final Integer id = ( Integer ) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		VendasController.getInstance().edit( id );
	}
}
