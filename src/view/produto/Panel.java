package view.produto;

import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel {
	private final JPanel jPanel;
	private final JLabel jLabel;
	private final JTextField jTextField;

	public Panel(String nome) {
		jPanel = new JPanel();

		jLabel = new JLabel(nome);
		jTextField = new JTextField(40);

		jPanel.add(jLabel);
		jPanel.add(jTextField);
	}

	public JPanel getPanel() {
		return this.jPanel;
	}

	public void setText(String texto) {
		this.jTextField.setText(texto);
	}

	public String getText() {
		return getTextField();
	}

	public Integer getInteger() {
		return new Integer(getTextField());
	}

	public BigDecimal getBigDecimal() {
		return new BigDecimal(getTextField());
	}

	private String getTextField() {
		return this.jTextField.getText();
	}

	public void requestFocus() {
		this.jTextField.requestFocus();
	}
}
