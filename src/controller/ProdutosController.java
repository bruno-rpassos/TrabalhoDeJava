package controller;

import java.util.List;

import javax.swing.JDialog;

import model.entity.Produto;
import model.factories.ProdutoFactory;
import view.vo.ProdutoVO;
import dao.ProdutoDAO;

public class ProdutosController {
	private Strinv DnvEssaString
	public static void newResource() {
		JDialog view = new view.produto.New();
		view.setVisible(true);
	}

	public static void edit() {
	}

	public static void create(ProdutoVO vo) {
		Produto novoProduto = ProdutoFactory.getProdutoByVO(vo);
		ProdutoDAO dao = ProdutoDAO.getInstance();
		dao.save(novoProduto);
	}

	public static void update() {
	}

	public static void destroy() {
	}

	public static void list() {
		JDialog view = new view.produto.Lista();
		view.setVisible(true);
	}
	
	public static List<ProdutoVO> getAllProdutos() {
		return ProdutoDAO.getInstance().list();
	}
}
