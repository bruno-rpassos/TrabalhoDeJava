package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.vo.ProdutoVO;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class Edit extends FormProduto {
	public Edit(final ProdutoVO vo) throws Exception {
		setTitle("ATUALIZAR PRODUTO");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterarProduto(vo);
			}
		});

		if (vo != null) {
			this.atualizaTextFieldsComVO(vo);
		}
	}

	private void alterarProduto(ProdutoVO vo) {
		try {
			ProdutosController.update(this.atualizaVO(vo));
			JOptionPane.showMessageDialog(this, "Produto atualizado!");
			this.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ">> ERRO AO ATUALIZAR PRODUTO");
		}
	}
}
