package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.Produto;
import view.vo.ProdutoVO;
import dao.ProdutoDAO;
import factory.ProdutoFactory;

public class ProdutosController {

	public static void create( final ProdutoVO vo ) {
		final Produto produto = ProdutoFactory.getProdutoByVO( vo );
		try {
			ProdutoDAO.getInstance().saveOrUpdate( produto );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static void edit( final String id ) {
		final ProdutoDAO dao = ProdutoDAO.getInstance();
		try {
			final Produto produto = dao.getById( new Integer( id ) );
			final JDialog view = new view.produto.EditProduto( ProdutoFactory.beanToVO( produto ) );
			view.setVisible( true );
		} catch ( final NumberFormatException e ) {
			e.printStackTrace();
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static List<ProdutoVO> getAllProdutosVO() {
		final List<Produto> produtos = ProdutoDAO.getInstance().list();
		final List<ProdutoVO> vo = new ArrayList<ProdutoVO>();

		for ( final Produto produto : produtos ) {
			vo.add( ProdutoFactory.beanToVO( produto ) );
		}

		return vo;
	}

	public static void list() {
		try {
			final JDialog view = new view.produto.ListaProduto();
			view.setVisible( true );
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	public static void newResource() {
		try {
			final JDialog view = new view.produto.NewProduto();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static void update( final ProdutoVO vo ) {
		final Produto produto = ProdutoFactory.getProdutoByVO( vo );
		try {
			ProdutoDAO.getInstance().saveOrUpdate( produto );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}
}
