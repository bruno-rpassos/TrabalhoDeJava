package controller;

import java.util.List;

import javax.swing.JDialog;

import model.Produto;
import dao.ProdutoDAO;
import exception.ProdutoInvalidException;
import exception.ProdutoNotFoundException;
import exception.TypeNotFoundException;

public class ProdutosController implements Controller<Produto> {

	private static ProdutosController	instance;

	public static ProdutosController getInstance() {
		if ( ProdutosController.instance == null ) ProdutosController.instance = new ProdutosController();
		return ProdutosController.instance;
	}

	private ProdutosController() {}

	@Override
	public void create( final Produto p ) {
		try {
			ProdutoDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {}
	}

	@Override
	public void edit( final Integer id ) throws TypeNotFoundException {
		try {
			final Produto produto = ProdutoDAO.getInstance().getById( id );
			System.out.println( "Produto found: " );
			System.out.println( "  > id " + produto.getId() );
			System.out.println( "  > ds " + produto.getDescricao() );
			System.out.println( "  > qt " + produto.getQuantidade() );
			System.out.println( "  > vl " + produto.getValor() );

			final JDialog view = new view.produto.EditProduto( produto );
			view.setVisible( true );
		} catch ( final ProdutoInvalidException e ) {} catch ( final ProdutoNotFoundException e ) {}
	}

	public void edit( final Produto p ) throws TypeNotFoundException, ProdutoNotFoundException {
		final JDialog view = new view.produto.EditProduto( p );
		view.setVisible( true );
	}

	@Override
	public Produto get( final Integer id ) {
		Produto p = null;

		try {
			p = ProdutoDAO.getInstance().getById( id );
		} catch ( final ProdutoInvalidException e ) {} catch ( final ProdutoNotFoundException e ) {}

		return p;
	}

	@Override
	public List<Produto> getAll() {
		return ProdutoDAO.getInstance().list();
	}

	@Override
	public void list() {
		try {
			final JDialog view = new view.produto.ListaProduto();
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
			final JDialog view = new view.produto.NewProduto();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update( final Produto p ) {
		try {
			ProdutoDAO.getInstance().saveOrUpdate( p );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}
}
