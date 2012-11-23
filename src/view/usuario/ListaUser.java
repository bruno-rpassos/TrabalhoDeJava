package view.usuario;

import controller.ProdutosController;
import view.tablemodel.UserTableModel;

@SuppressWarnings("serial")
public class ListaUser extends view.Lista<UserTableModel> {

	public ListaUser() throws InstantiationException, IllegalAccessException {
		super(UserTableModel.class);
	}

	@Override
	protected void doubleClicked() {
		String idDoUser = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		ProdutosController.edit(idDoUser);	
	}
}
