package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import controller.ProdutosController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance;
	private JMenuBar menuBar;

	private MainFrame() {
		menuBar = new JMenuBar();
		menuBar.add(CriadorDeMenu.criaMenuComSubMenu("Cadastro",
				subMenuCadastro()));
		menuBar.add(CriadorDeMenu.criaMenuComSubMenu("Listar", subMenuListar()));

		setJMenuBar(menuBar);

		setSize(200, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	private Map<String, ActionListener> subMenuListar() {
		Map<String, ActionListener> subMenu = new HashMap<String, ActionListener>();

		subMenu.put("Produto", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProdutosController.list();
			}
		});

		return subMenu;
	}

	private Map<String, ActionListener> subMenuCadastro() {
		Map<String, ActionListener> subMenu = new HashMap<String, ActionListener>();

		subMenu.put("Produto", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProdutosController.newResource();
			}
		});

		return subMenu;
	}

	public static void main(String[] args) {
		MainFrame mf = MainFrame.getInstance();
		mf.setVisible(true);
	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame();
		return instance;
	}
}
