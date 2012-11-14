package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import view.vo.ProdutoVO;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class Edit extends Form {
	public Edit(List<String> componentes, final ProdutoVO vo) {
		super(componentes);

		jbSalvar.addActionListener(new ActionListener() {
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
		ProdutosController.update(this.atualizaVO(vo));

		JOptionPane.showMessageDialog(this, "Produto alterado!");
		this.dispose();
	}
}
