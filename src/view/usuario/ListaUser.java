package view.usuario;

import view.tablemodel.UserTableModel;
import controller.ProdutosController;

@SuppressWarnings( "serial" )
public class ListaUser extends view.Lista<UserTableModel> {

	public ListaUser() throws InstantiationException, IllegalAccessException {
		super( UserTableModel.class );
	}

	@Override
	protected void doubleClicked() {
		final String idDoUser = String.valueOf( this.table.getValueAt( this.table.getSelectedRow(), 0 ) );
		ProdutosController.edit( idDoUser );
	}
}
