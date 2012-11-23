package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProdutosController;

@SuppressWarnings("serial")
public class New extends FormProduto {
	public New() throws Exception  {
		super();
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
			ProdutosController.create(this.parseVO());
			JOptionPane.showMessageDialog(this, "Produto cadastrado!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ">> ERRO AO CADASTRAR PRODUTO");
		}

		this.clear();
	}
}
