package view.produto;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;

import view.vo.ProdutoVO;

@SuppressWarnings("serial")
public class Form extends JDialog {
	private final Map<String, Panel> cPanels;
	protected JButton jbSalvar;

	public Form(List<String> componentes) {
		this.cPanels = new HashMap<String, Panel>();
		getContentPane().setLayout(new FlowLayout());
		
		for (String comp : componentes) {
			this.cPanels.put(comp, new Panel(comp));
			getContentPane().add(this.cPanels.get(comp).getPanel());
		}
		
		jbSalvar = new JButton("Salvar");
		getContentPane().add(jbSalvar);

		setSize(500, 300);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(jbSalvar);
	}

	protected void atualizaTextFieldsComVO(ProdutoVO vo) {
		this.cPanels.get("Descricao").setText(vo.getDescricao());
		this.cPanels.get("Quantidade").setText(vo.getQuantidade().toString());
		this.cPanels.get("Valor").setText(vo.getValor().toString());
	}

	protected ProdutoVO atualizaVO(ProdutoVO vo) {
		ProdutoVO alterado = getVO();
		alterado.setId(vo.getId());
		return alterado;
	}

	protected ProdutoVO getVO() {
		ProdutoVO vo = new ProdutoVO();
		vo.setDescricao(this.cPanels.get("Descricao").getText());
		vo.setQuantidade(this.cPanels.get("Quantidade").getInteger());
		vo.setValor(this.cPanels.get("Valor").getBigDecimal());
		return vo;
	}

	protected void limpar() {
		boolean focused = false;
		
		for (String panel : this.cPanels.keySet()) {
			this.cPanels.get(panel).setText("");
			if (!focused) {
				this.cPanels.get(panel).requestFocus();
				focused = true;
			}
		}
	}
}
