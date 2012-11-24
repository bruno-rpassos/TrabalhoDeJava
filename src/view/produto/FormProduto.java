package view.produto;

import view.Form;
import view.vo.ProdutoVO;

@SuppressWarnings( "serial" )
public class FormProduto extends Form<ProdutoVO> {

	public FormProduto() throws Exception {
		super( ProdutoVO.class, "PRODUTO" );
	}
}
