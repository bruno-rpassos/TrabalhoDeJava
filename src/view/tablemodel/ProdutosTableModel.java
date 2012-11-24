package view.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Listener;
import view.vo.ProdutoVO;
import controller.ProdutosController;
import dao.ProdutoDAO;

@SuppressWarnings( "serial" )
public class ProdutosTableModel extends DefaultTableModel {

	private static final String[]	COLUNAS	= new String[] { "Id", "Descricao", "Quantidade", "Valor" };

	private List<ProdutoVO>			listaDeProdutos;

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
			case ProdutoVO.ID:
				return Integer.class;
			case ProdutoVO.DESCRICAO:
				return String.class;
			case ProdutoVO.QUANTIDADE:
				return Integer.class;
			case ProdutoVO.VALOR:
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
		final ProdutoVO vo = this.listaDeProdutos.get( row );

		switch ( column ) {
			case ProdutoVO.ID:
				return vo.getId();
			case ProdutoVO.DESCRICAO:
				return vo.getDescricao();
			case ProdutoVO.QUANTIDADE:
				return vo.getQuantidade();
			case ProdutoVO.VALOR:
				return vo.getValor();
			default:
				return "";
		}
	}

	@Override
	public boolean isCellEditable( final int row, final int column ) {
		return false;
	}

	private void atualizarDados() {
		this.listaDeProdutos = new ArrayList<ProdutoVO>( ProdutosController.getAllProdutosVO() );
		this.fireTableDataChanged();
	}
}
