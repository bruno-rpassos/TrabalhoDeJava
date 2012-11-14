package view;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CriadorDeMenu {
	private static JMenu criaMenu(String nome) {
		return new JMenu(nome);
	}

	private static JMenuItem criaSubMenuComAction(String nome,
			ActionListener action) {
		JMenuItem subMenu = new JMenuItem(nome);
		subMenu.addActionListener(action);
		return subMenu;
	}

	public static JMenu criaMenuComSubMenu(String nomeMenu,
			Map<String, ActionListener> subMenuComAction) {
		JMenu menu = criaMenu(nomeMenu);

		for (String subMenu : subMenuComAction.keySet()) {
			ActionListener action = subMenuComAction.get(subMenu);
			menu.add(criaSubMenuComAction(subMenu, action));
		}

		return menu;
	}
}
