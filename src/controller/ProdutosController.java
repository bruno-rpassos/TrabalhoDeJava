package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.Produto;
import view.vo.ProdutoVO;
import dao.ProdutoDAO;
import factory.ProdutoFactory;

public class ProdutosController {

	public static void newResource() {
		try {
			JDialog view = new view.produto.NewProduto();
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void edit(String id) {
		ProdutoDAO dao = ProdutoDAO.getInstance();
		try {
			Produto produto = dao.getById(new Integer(id));
			JDialog view = new view.produto.EditProduto(
					ProdutoFactory.beanToVO(produto));
			view.setVisible(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create(ProdutoVO vo) {
		Produto produto = ProdutoFactory.getProdutoByVO(vo);
		try {
			ProdutoDAO.getInstance().saveOrUpdate(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update(ProdutoVO vo) {
		Produto produto = ProdutoFactory.getProdutoByVO(vo);
		try {
			ProdutoDAO.getInstance().saveOrUpdate(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void list() {
		try {
			JDialog view = new view.produto.ListaProduto();
			view.setVisible(true);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static List<ProdutoVO> getAllProdutosVO() {
		List<Produto> produtos = ProdutoDAO.getInstance().list();
		List<ProdutoVO> vo = new ArrayList<ProdutoVO>();

		for (Produto produto : produtos) {
			vo.add(ProdutoFactory.beanToVO(produto));
		}

		return vo;
	}
}
