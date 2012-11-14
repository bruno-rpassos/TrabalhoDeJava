package view.produto.model;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.listener.Listener;

import view.vo.ProdutoVO;
import controller.ProdutosController;
import dao.ProdutoDAO;

public class JListaDeProdutosTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] nomesDasColunas = new String[]{"Id", "Descricao", "Quantidade", "Valor"};
	private List<ProdutoVO> listaDeProdutos;
	
	public JListaDeProdutosTableModel() {
		ProdutoDAO.getInstance().addListener(new Listener() {
			public void actionPerformed() {
				atualizarDados();
			}
		});
		atualizarDados();
	}
	
	@Override
	public int getRowCount() {
		if(listaDeProdutos == null)
			return 0;
		return listaDeProdutos.size();
	}
	
	public int getColumnCount() {
		return nomesDasColunas.length;
	}
	
	private void atualizarDados() {
		this.listaDeProdutos = ProdutosController.getAllProdutos();
		fireTableDataChanged();
	}
	
	public Object getValueAt(int row, int column) {
		ProdutoVO p = this.listaDeProdutos.get(row);
		switch(column) {
			case 0: return p.getId();
			case 1: return p.getDescricao();
			case 2: return p.getQuantidade();
			case 3: return p.getValor();
			default: return "";
		}
	}
	
	public String getColumnName(int column) {
		return nomesDasColunas[column];
	}
	
	public Class<?> getColumnClass(int column) {
		switch(column) {
			case 0: return Integer.class;
			case 1: return String.class;
			case 2: return Integer.class;
			case 3: return BigDecimal.class;
			default: return Object.class;
		}
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
