package dao.repository;

import java.util.ArrayList;
import java.util.List;

import exception.ProdutoNotFoundException;

import model.entity.Produto;

public class ProdutoRepository {
	private static Integer				id	= 1;
	private static ProdutoRepository	repo;
	private List<Produto>				produtos;

	private ProdutoRepository() {
		this.produtos = new ArrayList<Produto>();
	}

	public static ProdutoRepository getInstance() {
		if (repo == null)
			repo = new ProdutoRepository();
		return repo;
	}

	public void add(Produto produto) throws Exception {
		if (produto.getId() == null) {
			produto.setId(new Integer(id++));
		} else {
			Produto existente = getById(produto.getId());
			if (existente != null) {
				this.produtos.remove(existente);
			}
		}
		
		this.produtos.add(produto);
	}

	public List<Produto> getAll() {
		return this.produtos;
	}

	public Produto getById(Integer id) throws Exception {
		for (Produto p : this.produtos) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		throw new ProdutoNotFoundException();
	}

}
