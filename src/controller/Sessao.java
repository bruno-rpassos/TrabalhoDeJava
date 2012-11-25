package controller;

import model.User;
import exception.PermissaoNegadaException;

public class Sessao {
	private static Sessao	instance;

	public static Sessao getInstance() {
		if ( Sessao.instance == null ) {
			Sessao.instance = new Sessao();
		}
		return Sessao.instance;
	}

	private User	user;

	private Sessao() {}

	public User getLogado() {
		return this.user;
	}

	public void setLogado( final User user ) throws PermissaoNegadaException {
		if ( this.temPermissaoLogar( user ) ) {
			this.user = user;
			return;
		}

		throw new PermissaoNegadaException( "logar" );
	}

	private boolean temPermissaoLogar( final User user ) {
		if ( PermissaoController.getInstance().isAdmin( user ) || PermissaoController.getInstance().isVendedor( user ) ) return true;

		return false;
	}
}
