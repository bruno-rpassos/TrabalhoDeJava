package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.Pessoa;
import dao.PessoaDAO;
import exception.ProdutoNotFoundException;
import exception.SQLException;
import exception.TypeNotFoundException;

public class PessoaController implements Controller<Pessoa> {

	private static PessoaController	instance;

	public static PessoaController getInstance() {
		if ( PessoaController.instance == null ) PessoaController.instance = new PessoaController();
		return PessoaController.instance;
	}

	private PessoaController() {}

	@Override
	public void create( final Pessoa p ) {
		try {
			PessoaDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {}
	}

	@Override
	public void edit( final Integer id ) throws SQLException, TypeNotFoundException {
		final Pessoa pessoa = PessoaDAO.getInstance().getById( id );
		final JDialog view = new view.pessoa.EditPessoa( pessoa );
		view.setVisible( true );
	}

	public void edit( final Pessoa p ) throws TypeNotFoundException, ProdutoNotFoundException {
		final JDialog view = new view.pessoa.EditPessoa( p );
		view.setVisible( true );
	}

	@Override
	public Pessoa get( final Integer id ) {
		Pessoa p = null;

		try {
			p = PessoaDAO.getInstance().getById( id );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public List<Pessoa> getAll() {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		try {
			lista = PessoaDAO.getInstance().list();
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void list() {
		try {
			final JDialog view = new view.pessoa.ListaPessoa();
			view.setVisible( true );
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void newResource() {
		try {
			final JDialog view = new view.pessoa.NewPessoa();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update( final Pessoa p ) {
		try {
			PessoaDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}
}
