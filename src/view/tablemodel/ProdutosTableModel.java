package view.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Listener;
import model.Produto;
import controller.ProdutosController;
import dao.ProdutoDAO;

@SuppressWarnings( "serial" )
public class ProdutosTableModel extends DefaultTableModel {

	private static final String[]	COLUNAS	= new String[] { "Id", "Descricao", "Quantidade", "Valor" };

	private List<Produto>			listaDeProdutos;

	public ProdutosTableModel() {
		ProdutoDAO.getInstance().addListener( new Listener() {
			@Override
			public void actionPerformed() {
				ProdutosTableModel.this.atualizarDados();
			}
		} );

		this.atualizarDados();
	}

	@Override
	public Class<?> getColumnClass( final int column ) {
		switch ( column ) {
			case Produto.ID:
				return Integer.class;
			case Produto.DESCRICAO:
				return String.class;
			case Produto.QUANTIDADE:
				return Integer.class;
			case Produto.VALOR:
				return BigDecimal.class;
			default:
				return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		return ProdutosTableModel.COLUNAS.length;
	}

	@Override
	public String getColumnName( final int column ) {
		return ProdutosTableModel.COLUNAS[column];
	}

	@Override
	public int getRowCount() {
		if ( this.listaDeProdutos == null ) return 0;
		return this.listaDeProdutos.size();
	}

	@Override
	public Object getValueAt( final int row, final int column ) {
		final Produto p = this.listaDeProdutos.get( row );

		switch ( column ) {
			case Produto.ID:
				return p.getId();
			case Produto.DESCRICAO:
				return p.getDescricao();
			case Produto.QUANTIDADE:
				return p.getQuantidade();
			case Produto.VALOR:
				return p.getValor();
			default:
				return "";
		}
	}

	@Override
	public boolean isCellEditable( final int row, final int column ) {
		return false;
	}

	private void atualizarDados() {
		this.listaDeProdutos = new ArrayList<Produto>( ProdutosController.getInstance().getAll() );
		this.fireTableDataChanged();
	}
}
