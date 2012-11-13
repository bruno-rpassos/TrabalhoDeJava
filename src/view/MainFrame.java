package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ProdutosController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance;
	private JMenuBar menuBar;

	private JMenu jmCadastro;
	private JMenuItem jmiCadastroProduto;

	private JMenu jmListagem;
	private JMenuItem jmiListarProduto;

	private MainFrame() {
		menuBar = new JMenuBar();

		jmCadastro = new JMenu("Cadastro");
		jmiCadastroProduto = new JMenuItem("Produto");
		jmiCadastroProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProdutosController.newResource();
			}
		});

		jmListagem = new JMenu("Listar");
		jmiListarProduto = new JMenuItem("Produto");
		jmiListarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProdutosController.list();
			}
		});

		jmCadastro.add(jmiCadastroProduto);
		jmListagem.add(jmiListarProduto);
		menuBar.add(jmCadastro);
		menuBar.add(jmListagem);
		setJMenuBar(menuBar);

		setSize(200, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		MainFrame mf = MainFrame.getInstance();
		mf.setVisible(true);
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
}
