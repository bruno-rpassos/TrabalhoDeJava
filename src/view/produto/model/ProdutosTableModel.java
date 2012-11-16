package view.produto.model;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.listener.Listener;
import view.vo.ProdutoVO;
import controller.ProdutosController;
import dao.ProdutoDAO;

@SuppressWarnings("serial")
public class ProdutosTableModel extends DefaultTableModel {
	
	private static final String[] COLUNAS = new String[] { "Id", "Descricao",
			"Quantidade", "Valor" };
	private static final int ID = 0;
	private static final int DESCRICAO = 1;
	private static final int QUANTIDADE = 2;
	private static final int VALOR = 3;

	private List<ProdutoVO> listaDeProdutos;

	public ProdutosTableModel() {
		ProdutoDAO.getInstance().addListener(new Listener() {
			public void actionPerformed() {
				atualizarDados();
			}
		});

		atualizarDados();
	}

	private void atualizarDados() {
		this.listaDeProdutos = ProdutosController.getAllProdutos();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if (listaDeProdutos == null)
			return 0;
		return listaDeProdutos.size();
	}

	@Override
	public int getColumnCount() {
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProdutoVO vo = this.listaDeProdutos.get(row);
		
		switch (column) {
		case ID:
			return vo.getId();
		case DESCRICAO:
			return vo.getDescricao();
		case QUANTIDADE:
			return vo.getQuantidade();
		case VALOR:
			return vo.getValor();
		default:
			return "";
		}
	}

	@Override
	public String getColumnName(int column) {
		return COLUNAS[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case ID:
			return Integer.class;
		case DESCRICAO:
			return String.class;
		case QUANTIDADE:
			return Integer.class;
		case VALOR:
			return BigDecimal.class;
		default:
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
