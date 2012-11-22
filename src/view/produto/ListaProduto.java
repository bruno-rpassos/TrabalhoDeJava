package view.produto;

import view.tablemodel.ProdutosTableModel;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class ListaProduto extends view.Lista<ProdutosTableModel> {

	public ListaProduto() throws InstantiationException, IllegalAccessException {
		super(ProdutosTableModel.class);
	}

	@Override
	protected void doubleClicked() {
		String idDoProduto = String.valueOf(table.getValueAt(
				table.getSelectedRow(), 0));
		ProdutosController.edit(idDoProduto);
	}
}