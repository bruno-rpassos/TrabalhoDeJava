package view.produto;

import view.tablemodel.ProdutosTableModel;
import controller.ProdutosController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaProduto extends view.Lista<ProdutosTableModel> {

	public ListaProduto() throws InstantiationException, IllegalAccessException {
		super( ProdutosTableModel.class );
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException, Exception {
		final Integer id = ( Integer ) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		System.out.println( "looking for produto with id " + id );
		ProdutosController.getInstance().edit( id );
	}
}
