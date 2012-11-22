package dao.repository;

import model.User;

public class Sessao {
	private static Sessao instance;
	private User logado;
	
	private Sessao() {}
	public static Sessao getInstance() {
		if(instance == null)
			instance = new Sessao();
		return instance;
	}
	
	public User getLogado() {
		return logado;
	}
	
	public void setLogado(User logado) {
		this.logado = logado;
	}
}
