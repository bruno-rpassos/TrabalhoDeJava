package repository;

import model.User;

public class Sessao {
	private static Sessao	instance;

	public static Sessao getInstance() {
		if ( Sessao.instance == null ) {
			Sessao.instance = new Sessao();
		}
		return Sessao.instance;
	}

	private User	logado;

	private Sessao() {}

	public User getLogado() {
		return this.logado;
	}

	public void setLogado( final User logado ) {
		this.logado = logado;
	}
}
