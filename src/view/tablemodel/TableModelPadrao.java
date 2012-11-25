package view.tablemodel;

import java.util.List;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings( "serial" )
public abstract class TableModelPadrao<T> extends DefaultTableModel {

	protected static String[]	COLUNAS	= {};

	protected List<T>			lista;

	@Override
	public abstract Class<?> getColumnClass( final int column );

	@Override
	public int getColumnCount() {
		return TableModelPadrao.COLUNAS.length;
	}

	@Override
	public String getColumnName( final int column ) {
		return TableModelPadrao.COLUNAS[column];
	}

	@Override
	public int getRowCount() {
		if ( this.lista == null ) return 0;
		return this.lista.size();
	}

	@Override
	public abstract Object getValueAt( final int row, final int column );

	@Override
	public boolean isCellEditable( final int row, final int column ) {
		return false;
	}

	protected abstract void atualizarDados();
}
