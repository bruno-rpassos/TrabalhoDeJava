package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class DialogPadrao extends JDialog {

	protected final JPanel contentPanel;

	public DialogPadrao(String title) {
		setTitle(title);

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setLayout(new FlowLayout());

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}
}
