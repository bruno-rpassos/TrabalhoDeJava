package factory;

import model.Produto;
import view.vo.ProdutoVO;

public class ProdutoFactory {

	public static ProdutoVO beanToVO( final Produto p ) {
		final ProdutoVO vo = new ProdutoVO();

		vo.setId( p.getId() );
		vo.setDescricao( p.getDescricao() );
		vo.setQuantidade( p.getQuantidade() );
		vo.setValor( p.getValor() );

		return vo;
	}

	public static Produto getProdutoByVO( final ProdutoVO vo ) {
		final Produto p = new Produto();

		p.setId( vo.getId() );
		p.setDescricao( vo.getDescricao() );
		p.setQuantidade( vo.getQuantidade() );
		p.setValor( vo.getValor() );

		return p;
	}
}
