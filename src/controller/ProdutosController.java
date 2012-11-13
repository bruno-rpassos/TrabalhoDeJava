package controller;
import java.util.List;
import javax.swing.JDialog;

import model.entity.Produto;
import model.factories.ProdutoFactory;
import view.vo.ProdutoVO;

public class ProdutosController {
	// WTF?
	public static void newResource() {
		JDialog view = new view.produto();
		view.setVisible(true);
	}

	public static void edit(String id) {
		ProdutoDAO dao = ProdutoDAO.getInstance();
		try {
			ProdutoVO vo = dao.getById(new Integer(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update(ProdutoVO vo) {
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
