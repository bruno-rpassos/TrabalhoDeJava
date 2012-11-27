package main;

import javax.swing.WindowConstants;

import model.Permissao;
import model.Produto;
import model.User;
import view.Login;
import controller.PermissaoController;
import controller.ProdutosController;
import controller.UserController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		Main.initialize();
	}

	private static void callLogin() {
		try {
			final Login dialog = new Login();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static void initialize() {
//		Main.initializePermissions();
//		Main.initializeUsers();
//		Main.initializeProdutos();
		Main.callLogin();
	}

	private static void initializePermissions() {
		PermissaoController.getInstance()
				.create(new Permissao(Permissao.ADMIN));
		PermissaoController.getInstance().create(
				new Permissao(Permissao.VENDEDOR));
	}

	private static void initializeProdutos() {
		for (int i = 1; i <= 5; i++) {
			final Produto p = new Produto();

			p.setDescricao("PRODUTO#" + i);
			p.setQuantidade(new Integer(i * 10));
			p.setValor(new Double(i * 20));

			ProdutosController.getInstance().create(p);
		}
	}

	private static void initializeUsers() {
		final User u = new User();

		u.setNome("admin");
		u.setSenha("123");
		u.setPermissao(PermissaoController.getInstance().get(Permissao.ADMIN));

		UserController.getInstance().create(u);
	}
}
