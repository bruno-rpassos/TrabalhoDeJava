package model.factories;

import model.entity.Produto;
import view.vo.ProdutoVO;

public class ProdutoFactory {

	public static Produto getProdutoByVO(ProdutoVO vo) {
		Produto p = new Produto();
		p.setId(vo.getId());
		p.setDescricao(vo.getDescricao());
		p.setQuantidade(vo.getQuantidade());
		p.setValor(vo.getValor());
		return p;
	}

	public static ProdutoVO beanToVO(Produto p) {
		ProdutoVO vo = new ProdutoVO();
		vo.setId(p.getId());
		vo.setDescricao(p.getDescricao());
		vo.setQuantidade(p.getQuantidade());
		vo.setValor(p.getValor());
		return vo;
	}

}
