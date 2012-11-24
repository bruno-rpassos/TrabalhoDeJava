package main;

import javax.swing.WindowConstants;

import model.User;
import view.Login;
import view.vo.ProdutoVO;
import controller.ProdutosController;
import controller.UserController;

public class Main {

	/**
	 * @param args
	 */
	public static void main( final String[] args ) {
		Main.initialize();
	}

	private static void callLogin() {
		try {
			final Login dialog = new Login();
			dialog.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	private static void initialize() {
		Main.initializeUsers();
		Main.initializeProdutos();
		Main.callLogin();
	}

	// >> BANCO
	private static void initializeProdutos() {
		for ( int i = 1; i <= 5; i++ ) {
			final ProdutoVO vo = new ProdutoVO();
			vo.setDescricao( "PRODUTO#" + i );
			vo.setQuantidade( new Integer( i * 10 ) );
			vo.setValor( new Double( i * 20 ) );
			ProdutosController.create( vo );
		}
	}

	// >> BANCO
	private static void initializeUsers() {
		final User u = new User();
		u.setNome( "admin" );
		u.setSenha( "123" );
		UserController.registerNewUser( u );
	}

}
