package view.produto;

import view.Form;
import view.vo.ProdutoVO;

@SuppressWarnings("serial")
public class FormProduto extends Form<ProdutoVO> {

	public FormProduto() throws Exception {
		super(ProdutoVO.class, "PRODUTO");
	}

	protected ProdutoVO getVO() throws Exception {
		ProdutoVO vo = new ProdutoVO();

		// - > REFLECTION
		// vo.setDescricao(new String(this.tfDescricao.getText()));
		// vo.setQuantidade(new Integer(this.tfQuantidade.getText()));
		// vo.setValor(new BigDecimal(this.tfValor.getText()));

		return vo;
	}

	protected ProdutoVO atualizaVO(ProdutoVO vo) throws Exception {
		ProdutoVO alterado = getVO();
		alterado.setId(vo.getId());
		return alterado;
	}

	protected void atualizaTextFieldsComVO(ProdutoVO vo) {
		// - > REFLECTION
		// this.tfDescricao.setText(vo.getDescricao());
		// this.tfQuantidade.setText(vo.getQuantidade().toString());
		// this.tfValor.setText(vo.getValor().toString());
	}
}
