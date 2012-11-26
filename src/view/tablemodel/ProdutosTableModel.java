package view.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Listener;
import model.Produto;
import controller.ProdutosController;
import dao.ProdutoDAO;

@SuppressWarnings( "serial" )
public class ProdutosTableModel extends TableModelPadrao<Produto> {

	public ProdutosTableModel() throws Exception {
		TableModelPadrao.COLUNAS = new String[] { "Id", "Descricao", "Quantidade", "Valor" };

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
	public Object getValueAt( final int row, final int column ) {
		final Produto p = this.lista.get( row );

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
	protected void atualizarDados() {
		this.lista = new ArrayList<Produto>( ProdutosController.getInstance().getAll() );
		this.fireTableDataChanged();
	}
}
