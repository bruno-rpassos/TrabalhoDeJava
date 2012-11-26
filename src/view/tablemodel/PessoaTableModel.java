package view.tablemodel;

import java.util.ArrayList;

import model.Listener;
import model.Pessoa;
import controller.PessoaController;
import dao.ProdutoDAO;

@SuppressWarnings( "serial" )
public class PessoaTableModel extends TableModelPadrao<Pessoa> {

	public PessoaTableModel() throws Exception {
		TableModelPadrao.COLUNAS = new String[] { "Id", "Nome", "CPF", "Endereço", "Cidade / Estado", "Limite de Renda" };

		ProdutoDAO.getInstance().addListener( new Listener() {
			@Override
			public void actionPerformed() {
				PessoaTableModel.this.atualizarDados();
			}
		} );

		this.atualizarDados();
	}

	@Override
	public Class<?> getColumnClass( final int column ) {
		switch ( column ) {
			case Pessoa.ID:
				return Integer.class;
			case Pessoa.NOME:
				return String.class;
			case Pessoa.CPF:
				return String.class;
			case Pessoa.ENDERECO:
				return String.class;
			case Pessoa.CIDADE:
				return String.class;
			case Pessoa.LIMITE:
				return Double.class;
			default:
				return Object.class;
		}
	}

	@Override
	public Object getValueAt( final int row, final int column ) {
		final Pessoa p = this.lista.get( row );

		switch ( column ) {
			case Pessoa.ID:
				return p.getId();
			case Pessoa.NOME:
				return p.getNome();
			case Pessoa.CPF:
				return p.getCpf();
			case Pessoa.ENDERECO:
				return p.getEndereco();
			case Pessoa.CIDADE:
				return p.getCidadeEstado();
			case Pessoa.LIMITE:
				return p.getLimiteCredito();
			default:
				return "";
		}
	}

	@Override
	protected void atualizarDados() {
		this.lista = new ArrayList<Pessoa>( PessoaController.getInstance().getAll() );
		this.fireTableDataChanged();
	}
}
