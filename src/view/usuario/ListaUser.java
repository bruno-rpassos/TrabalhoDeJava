package view.usuario;

import view.tablemodel.UserTableModel;
import controller.ProdutosController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaUser extends view.Lista<UserTableModel> {

	public ListaUser() throws InstantiationException, IllegalAccessException {
		super( UserTableModel.class );
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException {
		final Integer id = Integer.parseInt( ( String ) this.table.getValueAt( this.table.getSelectedRow(), 0 ) );
		ProdutosController.getInstance().edit( id );
	}
}
