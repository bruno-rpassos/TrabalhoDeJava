package main;

import java.math.BigDecimal;

import javax.swing.JDialog;

import model.entity.User;
import view.login.Login;
import view.vo.ProdutoVO;
import controller.login.UserController;
import controller.produto.ProdutosController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize();
	}

	private static void initialize() {
		initializeUsers();
		initializeProdutos();
		callLogin();
	}

	// >> BANCO
	private static void initializeUsers() {
		User u = new User("admin");
		String p = "123";
		UserController.registerNewUserWithPassword(u, p);
	}

	// >> BANCO
	private static void initializeProdutos() {
		for (int i = 1; i <= 5; i++) {
			ProdutoVO vo = new ProdutoVO();
			vo.setDescricao("PRODUTO#" + i);
			vo.setQuantidade(i * 10);
			vo.setValor(new BigDecimal(i * 20));
			ProdutosController.create(vo);
		}
	}

	private static void callLogin() {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
