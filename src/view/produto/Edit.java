package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import view.vo.ProdutoVO;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class Edit extends Form {
	public Edit(ProdutoVO vo) {
		jbSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
			}

		});
		
		if(vo != null) {
			this.jtfDescricao.setText(vo.getDescricao());
			this.jtfQuantidade.setText(vo.getQuantidade().toString());
			this.jtfValor.setText(vo.getValor().toString());
		}
	}
	
	private void alterarProduto() {
		ProdutoVO vo = new ProdutoVO();
		vo.setDescricao(jtfDescricao.getText());
		vo.setQuantidade(new Integer(jtfQuantidade.getText()));
		vo.setValor(new BigDecimal(jtfValor.getText()));
		ProdutosController.update(vo);
		JOptionPane.showMessageDialog(this, "Produto cadastrado!");
		jtfDescricao.setText("");
		jtfQuantidade.setText("");
		jtfValor.setText("");
	}
}
