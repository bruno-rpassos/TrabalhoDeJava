package view.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Listener;
import view.vo.UserVO;
import controller.UserController;
import dao.UserDAO;

@SuppressWarnings("serial")
public class UsuarioTableModel extends DefaultTableModel {
	private static final String[] COLUNAS = new String[] { "Id", "Descricao",
			"Quantidade", "Valor" };

	private List<UserVO> listaDeUsers;

	public UsuarioTableModel() {
		UserDAO.getInstance().addListener(new Listener() {
			public void actionPerformed() {
				atualizarDados();
			}
		});

		atualizarDados();
	}

	private void atualizarDados() {
		this.listaDeUsers = new ArrayList<UserVO>(UserController.getAllUserVO());
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if (listaDeUsers == null)
			return 0;
		return listaDeUsers.size();
	}

	@Override
	public int getColumnCount() {
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		UserVO vo = this.listaDeUsers.get(row);

		switch (column) {
		case UserVO.ID:
			return vo.getId();
		case UserVO.NOME:
			return vo.getNome();
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
		case UserVO.ID:
			return Integer.class;
		case UserVO.NOME:
			return String.class;
		default:
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
