package view.tablemodel;

import java.util.ArrayList;

import model.Listener;
import model.Venda;
import controller.VendasController;
import dao.VendaDAO;

@SuppressWarnings( "serial" )
public class VendasTableModel extends TableModelPadrao<Venda> {

	public VendasTableModel() {
		TableModelPadrao.COLUNAS = new String[] { "Id", "Descricao", "Valor Total (R$)", "Desconto (%)" };

		VendaDAO.getInstance().addListener( new Listener() {
			@Override
			public void actionPerformed() {
				VendasTableModel.this.atualizarDados();
			}
		} );

		this.atualizarDados();
	}

	@Override
	public Class<?> getColumnClass( final int column ) {
		switch ( column ) {
			case Venda.ID:
				return Integer.class;
			case Venda.DESCRICAO:
				return String.class;
			case Venda.VALOR_TOTAL:
				return Integer.class;
			case Venda.DESCONTO:
				return Integer.class;
			default:
				return Object.class;
		}
	}

	@Override
	public Object getValueAt( final int row, final int column ) {
		final Venda v = this.lista.get( row );

		switch ( column ) {
			case Venda.ID:
				return v.getId();
			case Venda.DESCRICAO:
				return v.getDescricao();
			case Venda.VALOR_TOTAL:
				return v.getValorTotal();
			case Venda.DESCONTO:
				return v.getDesconto();
			default:
				return "";
		}
	}

	@Override
	public boolean isCellEditable( final int row, final int column ) {
		return false;
	}

	@Override
	protected void atualizarDados() {
		this.lista = new ArrayList<Venda>( VendasController.getInstance().getAll() );
		this.fireTableDataChanged();
	}
}
