package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.entity.Produto;
import model.factories.ProdutoFactory;
import view.vo.ProdutoVO;
import dao.ProdutoDAO;

public class ProdutosController {
	
	private List<String> componentes;
	private static ProdutosController instance;
	
	private ProdutosController() {
		componentes = new ArrayList<String>();
		componentes.add("Descricao");
		componentes.add("Quantidade");
		componentes.add("Valor");
	}
	
	public static ProdutosController getInstance() {
		if (instance == null) instance = new ProdutosController();
		return instance;
	}

	public static void newResource() {
		JDialog view = new view.produto.New(componentes);
		view.setVisible(true);
	}

	public static void edit(String id) {
		ProdutoDAO dao = ProdutoDAO.getInstance();
		try {
			ProdutoVO vo = dao.getById(new Integer(id));
			JDialog view = new view.produto.Edit(componentes, vo);
			view.setVisible(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create(ProdutoVO vo) {
		Produto novoProduto = ProdutoFactory.getProdutoByVO(vo);
		try {
			ProdutoDAO.getInstance().saveOrUpdate(novoProduto);
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

	public static List<ProdutoVO> getAllProdutos() {
		return ProdutoDAO.getInstance().list();
	}
}
