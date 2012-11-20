package view.produto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.listener.Listener;
import view.vo.ProdutoVO;
import controller.ProdutosController;
import dao.ProdutoDAO;

@SuppressWarnings("serial")
public class ProdutosTableModel extends DefaultTableModel {

	private static final String[]	COLUNAS	= new String[] { "Id", "Descricao", "Quantidade", "Valor" };

	private List<ProdutoVO>			listaDeProdutos;

	public ProdutosTableModel() {
		ProdutoDAO.getInstance().addListener(new Listener() {
			public void actionPerformed() {
				atualizarDados();
			}
		});

		atualizarDados();
	}

	private void atualizarDados() {
		this.listaDeProdutos = new ArrayList<ProdutoVO>(ProdutosController.getAllProdutos());
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
	public String getColumnName(int column) {
		return COLUNAS[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
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
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
