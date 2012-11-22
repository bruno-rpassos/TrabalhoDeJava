package dao;

import java.util.ArrayList;
import java.util.List;

import model.entity.Produto;
import model.listener.Listener;
import dao.repository.ProdutoRepository;

public class ProdutoDAO implements DAO<Produto> {
	private static ProdutoDAO	instance;
	private List<Listener>		listeners;

	private ProdutoDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public static ProdutoDAO getInstance() {
		if (instance == null)
			instance = new ProdutoDAO();
		return instance;
	}

	public void saveOrUpdate(Produto produto) throws Exception {
		System.out.println(produto);
		
		ProdutoRepository.getInstance().add(produto);
		dataChanged();
	}

	public Produto getById(Integer id) throws Exception {
		return ProdutoRepository.getInstance().getById(id);
	}

	public List<Produto> list() {
		List<Produto> produtos = ProdutoRepository.getInstance().getAll();
		return produtos;
	}

	public void addListener(Listener l) {
		this.listeners.add(l);
	}

	private void dataChanged() {
		for (Listener l : this.listeners) {
			l.actionPerformed();
		}
	}

}
