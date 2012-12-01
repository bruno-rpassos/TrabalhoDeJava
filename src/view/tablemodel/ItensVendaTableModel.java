package view.tablemodel;

import java.math.BigDecimal;

import model.Listener;
import model.Produto;
import model.Venda;
import dao.VendaDAO;

@SuppressWarnings("serial")
public class ItensVendaTableModel extends TableModelPadrao<Produto> {
	private Venda venda;

	public ItensVendaTableModel(final Venda venda) throws Exception {
		TableModelPadrao.COLUNAS = new String[] { "Id", "Descricao",
				"Quantidade", "Valor" };
		this.venda = venda;

		VendaDAO.getInstance().addListener(new Listener() {
			@Override
			public void actionPerformed() {
				ItensVendaTableModel.this.atualizarDados();
			}
		});

		this.atualizarDados();
	}

	public void addProduto(final Produto p) {
		this.venda.addProduto(p);
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case Produto.ID:
				return "ID";
			case Produto.DESCRICAO:
				return "DESCRICAO";
			case Produto.QUANTIDADE:
				return "QUANTIDADE";
			case Produto.VALOR:
				return "VALOR";
			default:
				return "";
		}
	}

	@Override
	public Class<?> getColumnClass(final int column) {
		switch (column) {
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
	public Object getValueAt(final int row, final int column) {
		final Produto p = this.lista.get(row);

		switch (column) {
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

	public void NewVenda() {
		this.venda = new Venda();
		this.atualizarDados();
	}

	@Override
	protected void atualizarDados() {
		this.lista = this.venda.getProdutos();
		this.fireTableDataChanged();
	}
}
