package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProdutosController;

@SuppressWarnings("serial")
public class New extends Form {
	public New() {
		setTitle("NOVO PRODUTO");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarProduto();
			}
		});
	}

	private void salvarProduto() {
		try {
			ProdutosController.create(this.getVO());
			JOptionPane.showMessageDialog(this, "Produto cadastrado!");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ">> ERRO AO CADASTRAR PRODUTO");
		}

		this.clear();
	}
}
