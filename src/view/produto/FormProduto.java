package view.produto;

import view.Form;
import view.vo.ProdutoVO;

@SuppressWarnings("serial")
public class FormProduto extends Form<ProdutoVO> {

	public FormProduto() throws Exception {
		super(ProdutoVO.class, "PRODUTO");
	}

	protected ProdutoVO parseVO() throws Exception {
		ProdutoVO vo = super.generateVO();
		return vo;
	}

	protected ProdutoVO update(ProdutoVO vo) throws Exception {
		ProdutoVO alterado = parseVO();
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
