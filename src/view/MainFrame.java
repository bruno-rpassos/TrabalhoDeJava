package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ProdutosController;
import controller.UserController;

public class MainFrame {

	public JFrame	frmMain;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmMain = new JFrame();
		this.frmMain.getContentPane().setBackground( Color.DARK_GRAY );
		this.frmMain.setResizable( false );
		this.frmMain.setTitle( "2SI 2012 - ProgII - 4Bim" );
		this.frmMain.setBounds( new Rectangle( 100, 100, 500, 200 ) );
		this.frmMain.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// INICIO >> MENUBAR
		{
			final JMenuBar menuBar = new JMenuBar();
			menuBar.setBackground( Color.DARK_GRAY );
			this.frmMain.setJMenuBar( menuBar );

			// INICIO >> MENU NOVO
			{
				final JMenu mnNovo = new JMenu( "NOVO" );
				mnNovo.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
				mnNovo.setBackground( Color.DARK_GRAY );
				mnNovo.setForeground( Color.WHITE );
				menuBar.add( mnNovo );

				// INICIO >> MENUITEM VENDA
				{
					final JMenuItem mntmVenda = new JMenuItem( "Venda" );
					mntmVenda.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
					mnNovo.add( mntmVenda );
				}
				// FIM << MENUITEM VENDA

				// INICIO >> MENUITEM PRODUTO
				{
					final JMenuItem mntmProduto = new JMenuItem( "Produto" );
					mntmProduto.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
					mntmProduto.addActionListener( new ActionListener() {
						@Override
						public void actionPerformed( final ActionEvent e ) {
							ProdutosController.getInstance().newResource();
						}
					} );
					mnNovo.add( mntmProduto );
				}
				// FIM << MENUITEM PRODUTO

				// INICIO >> MENUITEM USER
				{
					final JMenuItem mntmProduto = new JMenuItem( "Usuario" );
					mntmProduto.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
					mntmProduto.addActionListener( new ActionListener() {
						@Override
						public void actionPerformed( final ActionEvent e ) {
							UserController.getInstance().newResource();
						}
					} );
					mnNovo.add( mntmProduto );
				}
				// FIM << MENUITEM USER
			}
			// FIM << MENU NOVO

			// INICIO >> MENU RELATORIO
			{
				final JMenu mnRelatorio = new JMenu( "RELATORIO" );
				mnRelatorio.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
				mnRelatorio.setBackground( Color.DARK_GRAY );
				mnRelatorio.setForeground( Color.WHITE );
				menuBar.add( mnRelatorio );

				// INICIO >> MENUITEM VENDAS
				{
					final JMenuItem mntmVendas = new JMenuItem( "Vendas" );
					mntmVendas.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
					mnRelatorio.add( mntmVendas );
				}
				// FIM << MENUITEM VENDAS

				// INICIO >> MENUITEM PRODUTOS
				{
					final JMenuItem mntmProdutos = new JMenuItem( "Produtos" );
					mntmProdutos.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
					mntmProdutos.addActionListener( new ActionListener() {
						@Override
						public void actionPerformed( final ActionEvent e ) {
							ProdutosController.getInstance().list();
						}
					} );
					mnRelatorio.add( mntmProdutos );
				}
				// FIM << MENUITEM VENDAS

			}
			// FIM << MENU RELATORIO

			// INICIO >> LOGOUT
			{
				final JMenuItem mnNovo = new JMenuItem( "LOGOUT" );
				mnNovo.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
				mnNovo.setBackground( Color.DARK_GRAY );
				mnNovo.setForeground( Color.WHITE );
				mnNovo.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed( final ActionEvent e ) {
						new Login().setVisible( true );
						MainFrame.this.frmMain.dispose();
					}
				} );
				menuBar.add( mnNovo );
			}
			// FIM << LOGOUT
		}
		// FIM << MENUBAR

	}
}
