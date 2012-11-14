package view.produto;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

import view.vo.ProdutoVO;

@SuppressWarnings("serial")
public class Form extends JDialog {
	private final Panel cDescricao;
	private final Panel cQuantidade;
	private final Panel cValor;

	protected JButton jbSalvar;

	public Form() {
		this.cDescricao = new Panel("Descrição");
		this.cQuantidade = new Panel("Quantidade");
		this.cValor = new Panel("Valor");

		jbSalvar = new JButton("Salvar");

		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(cDescricao.getPanel());
		getContentPane().add(cQuantidade.getPanel());
		getContentPane().add(cValor.getPanel());
		getContentPane().add(jbSalvar);

		setSize(500, 300);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(jbSalvar);
	}

	protected void atualizaTextFieldsComVO(ProdutoVO vo) {
		this.cDescricao.setText(vo.getDescricao());
		this.cQuantidade.setText(vo.getQuantidade().toString());
		this.cValor.setText(vo.getValor().toString());
	}

	protected ProdutoVO atualizaVO(ProdutoVO vo) {
		ProdutoVO alterado = getVO();
		alterado.setId(vo.getId());
		return alterado;
	}

	protected ProdutoVO getVO() {
		ProdutoVO vo = new ProdutoVO();
		vo.setDescricao(this.cDescricao.getText());
		vo.setQuantidade(this.cQuantidade.getInteger());
		vo.setValor(this.cValor.getBigDecimal());
		return vo;
	}

	protected void limpar() {
		this.cDescricao.setText("");
		this.cQuantidade.setText("");
		this.cValor.setText("");
		this.cDescricao.requestFocus();
	}
}
