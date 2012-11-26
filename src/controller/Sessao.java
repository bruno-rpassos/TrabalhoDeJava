package controller;

import model.User;
import exception.PermissaoNegadaException;

public class Sessao {
	private static Sessao	instance;

	public static Sessao getInstance() {
		if ( Sessao.instance == null ) Sessao.instance = new Sessao();
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

		throw new PermissaoNegadaException( "LOGAR" );
	}

	public void temPermissaoCriarUser() throws PermissaoNegadaException {
		if ( this.isAdmin() ) return;

		throw new PermissaoNegadaException( "CRIAR NOVO USUARIO" );
	}

	public void temPermissaoCriarVenda() throws PermissaoNegadaException {
		if ( this.isAdmin() || this.isVendedor() ) return;

		throw new PermissaoNegadaException( "CRIAR NOVA VENDA" );
	}

	public void temPermissaoEditarVenda() throws PermissaoNegadaException {
		if ( this.isAdmin() ) return;

		throw new PermissaoNegadaException( "ALTERAR VENDA SALVA" );
	}

	public void temPermissaoListarVenda() throws PermissaoNegadaException {
		if ( this.isAdmin() || this.isVendedor() ) return;

		throw new PermissaoNegadaException( "LISTAR VENDAS CADASTRADAS" );
	}

	private boolean isAdmin() {
		return PermissaoController.getInstance().isAdmin( this.user );
	}

	private boolean isVendedor() {
		return PermissaoController.getInstance().isVendedor( this.user );
	}

	private boolean temPermissaoLogar( final User user ) {
		return PermissaoController.getInstance().isAdmin( user ) || PermissaoController.getInstance().isVendedor( user );
	}
}
