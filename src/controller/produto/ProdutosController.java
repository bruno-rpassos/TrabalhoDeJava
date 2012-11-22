package controller.produto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.entity.Produto;
import model.factories.ProdutoFactory;
import view.vo.ProdutoVO;
import dao.ProdutoDAO;

public class ProdutosController {

	public static void newResource() {
		JDialog view = new view.produto.New();
		view.setVisible(true);
	}

	public static void edit(String id) {
		ProdutoDAO dao = ProdutoDAO.getInstance();
		try {
			Produto produto = dao.getById(new Integer(id));
			JDialog view = new view.produto.Edit(ProdutoFactory.beanToVO(produto));
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

	public static void destroy() {
	}

	public static void list() {
		JDialog view = new view.produto.Lista();
		view.setVisible(true);
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
