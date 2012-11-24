package view.produto;

import view.tablemodel.ProdutosTableModel;
import controller.ProdutosController;

@SuppressWarnings( "serial" )
public class ListaProduto extends view.Lista<ProdutosTableModel> {

	public ListaProduto() throws InstantiationException, IllegalAccessException {
		super( ProdutosTableModel.class );
	}

	@Override
	protected void doubleClicked() {
		final String idDoProduto = String.valueOf( this.table.getValueAt( this.table.getSelectedRow(), 0 ) );
		ProdutosController.edit( idDoProduto );
	}
}
