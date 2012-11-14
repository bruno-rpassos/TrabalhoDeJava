package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.vo.ProdutoVO;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class New extends Form {
	public New() {
		jbSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarProduto();
			}
		});
	}

	private void salvarProduto() {
		ProdutoVO vo = this.getVO();

		ProdutosController.create(vo);
		JOptionPane.showMessageDialog(this, "Produto cadastrado!");

		this.limpar();
	}
}
