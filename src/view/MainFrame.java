package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.vo.ProdutoVO;
import controller.ProdutosController;

public class MainFrame {

	private JFrame	frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		initializeRepositoryTest();
	}

	private void initializeRepositoryTest() {
		for (int i = 1; i <= 50; i++) {
			ProdutoVO vo = new ProdutoVO();
			vo.setDescricao("PRODUTO#" + i);
			vo.setQuantidade(i * 10);
			vo.setValor(new BigDecimal(i * 20));
			ProdutosController.create(vo);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.getContentPane().setBackground(Color.DARK_GRAY);
		frmMain.setResizable(false);
		frmMain.setTitle("2SI 2012 - ProgII - 4Bim");
		frmMain.setBounds(new Rectangle(100, 100, 500, 200));
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// INICIO >> MENUBAR
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBackground(Color.DARK_GRAY);
			frmMain.setJMenuBar(menuBar);

			// INICIO >> MENU NOVO
			{
				JMenu mnNovo = new JMenu("NOVO");
				mnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				mnNovo.setBackground(Color.DARK_GRAY);
				mnNovo.setForeground(Color.WHITE);
				menuBar.add(mnNovo);

				// INICIO >> MENUITEM VENDA
				{
					JMenuItem mntmVenda = new JMenuItem("Venda");
					mntmVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mnNovo.add(mntmVenda);
				}
				// FIM << MENUITEM VENDA

				// INICIO >> MENUITEM PRODUTO
				{
					JMenuItem mntmProduto = new JMenuItem("Produto");
					mntmProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmProduto.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ProdutosController.newResource();
						}
					});
					mnNovo.add(mntmProduto);
				}
				// FIM << MENUITEM PRODUTO

			}
			// FIM << MENU NOVO

			// INICIO >> MENU RELATORIO
			{
				JMenu mnRelatorio = new JMenu("RELATORIO");
				mnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				mnRelatorio.setBackground(Color.DARK_GRAY);
				mnRelatorio.setForeground(Color.WHITE);
				menuBar.add(mnRelatorio);

				// INICIO >> MENUITEM VENDAS
				{
					JMenuItem mntmVendas = new JMenuItem("Vendas");
					mntmVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mnRelatorio.add(mntmVendas);
				}
				// FIM << MENUITEM VENDAS

				// INICIO >> MENUITEM PRODUTOS
				{
					JMenuItem mntmProdutos = new JMenuItem("Produtos");
					mntmProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmProdutos.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ProdutosController.list();
						}
					});
					mnRelatorio.add(mntmProdutos);
				}
				// FIM << MENUITEM VENDAS

			}
			// FIM << MENU RELATORIO

		}
		// FIM << MENUBAR

	}
}
