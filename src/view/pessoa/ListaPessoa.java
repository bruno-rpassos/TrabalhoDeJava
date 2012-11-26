package view.pessoa;

import view.tablemodel.PessoaTableModel;
import controller.PessoaController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class ListaPessoa extends view.Lista<PessoaTableModel> {

	public ListaPessoa() throws InstantiationException, IllegalAccessException {
		super( PessoaTableModel.class );
	}

	@Override
	protected void doubleClicked() throws TypeNotFoundException, Exception {
		final Integer id = ( Integer ) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		System.out.println( "looking for pessoa with id " + id );
		PessoaController.getInstance().edit( id );
	}
}
