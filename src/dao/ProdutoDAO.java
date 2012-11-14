package dao;

import java.util.ArrayList;
import java.util.List;

import view.vo.ProdutoVO;
import model.entity.Produto;
import model.factories.ProdutoFactory;
import model.listener.Listener;

public class ProdutoDAO {
	private static ProdutoDAO instance;
	private List<Listener> listeners;
	
	private ProdutoDAO() {
		this.listeners = new ArrayList<Listener>();
	}
	
	public static ProdutoDAO getInstance() {
		if(instance == null)
			instance = new ProdutoDAO();
		return instance;
	}

	public void saveOrUpdate(Produto produto) throws Exception {
		ProdutoRepository.getInstance().add(produto);
		dataChanged();
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
	
	public void addListener(Listener l) {
		this.listeners.add(l);
	}
	
	private void dataChanged() {
		for(Listener l : this.listeners) {
			l.actionPerformed();
		}
	}

}
