package dao;

import java.util.ArrayList;
import java.util.List;

import view.vo.ProdutoVO;
import model.entity.Produto;
import model.factories.ProdutoFactory;

public class ProdutoDAO {
	private static ProdutoDAO instance;
	
	private ProdutoDAO() {}
	
	public static ProdutoDAO getInstance() {
		if(instance == null)
			instance = new ProdutoDAO();
		return instance;
	}

	public void saveOrUpdate(Produto produto) throws Exception {
		ProdutoRepository.getInstance().add(produto);		
	}
	
	public ProdutoVO getById(Integer id) throws Exception {
		return ProdutoFactory.beanToVO(ProdutoRepository.getInstance().getById(id));
	}

	public List<ProdutoVO> list() {
		List<Produto> produtos = ProdutoRepository.getInstance().getAll();
		List<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		
		for(Produto p : produtos) {
			produtosVO.add(ProdutoFactory.beanToVO(p));
		}
		return produtosVO;
	}

}
