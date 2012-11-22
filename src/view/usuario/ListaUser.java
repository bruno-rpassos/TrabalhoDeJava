package view.usuario;

import controller.ProdutosController;
import view.tablemodel.UsuarioTableModel;

@SuppressWarnings("serial")
public class ListaUser extends view.Lista<UsuarioTableModel> {

	public ListaUser() throws InstantiationException, IllegalAccessException {
		super(UsuarioTableModel.class);
	}

	@Override
	protected void doubleClicked() {
		String idDoUser = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		ProdutosController.edit(idDoUser);	
	}
}
