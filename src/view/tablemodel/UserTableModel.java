package view.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Listener;
import view.vo.UserVO;
import controller.UserController;
import dao.UserDAO;

@SuppressWarnings( "serial" )
public class UserTableModel extends DefaultTableModel {
	private static final String[]	COLUNAS	= new String[] { "Id", "Descricao", "Quantidade", "Valor" };

	private List<UserVO>			listaDeUsers;

	public UserTableModel() {
		UserDAO.getInstance().addListener( new Listener() {
			@Override
			public void actionPerformed() {
				UserTableModel.this.atualizarDados();
			}
		} );

		this.atualizarDados();
	}

	@Override
	public Class<?> getColumnClass( final int column ) {
		switch ( column ) {
			case UserVO.ID:
				return Integer.class;
			case UserVO.NOME:
				return String.class;
			default:
				return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		return UserTableModel.COLUNAS.length;
	}

	@Override
	public String getColumnName( final int column ) {
		return UserTableModel.COLUNAS[column];
	}

	@Override
	public int getRowCount() {
		if ( this.listaDeUsers == null ) return 0;
		return this.listaDeUsers.size();
	}

	@Override
	public Object getValueAt( final int row, final int column ) {
		final UserVO vo = this.listaDeUsers.get( row );

		switch ( column ) {
			case UserVO.ID:
				return vo.getId();
			case UserVO.NOME:
				return vo.getNome();
			default:
				return "";
		}
	}

	@Override
	public boolean isCellEditable( final int row, final int column ) {
		return false;
	}

	private void atualizarDados() {
		this.listaDeUsers = new ArrayList<UserVO>( UserController.getAllUserVO() );
		this.fireTableDataChanged();
	}
}
