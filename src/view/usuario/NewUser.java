package view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Permissao;
import model.User;
import controller.PermissaoController;
import controller.UserController;

@SuppressWarnings( "serial" )
public class NewUser extends FormUser {

	private Integer	tipoPermissao	= Permissao.VENDEDOR;

	public NewUser() throws Exception {
		super();
		this.setTitle( "NOVO USER" );

		this.initializeRadioButtons();

		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				NewUser.this.salvarUser();
			}
		} );
	}

	private void initializeRadioButtons() {
		final JPanel panel = new JPanel();

		final JRadioButton rdbtnAdministrador = new JRadioButton( "Administrador" );
		panel.add( rdbtnAdministrador );

		final JRadioButton rdbtnVendedor = new JRadioButton( "Vendedor" );
		rdbtnVendedor.setEnabled( true );
		panel.add( rdbtnVendedor );

		final ButtonGroup group = new ButtonGroup();
		group.add( rdbtnAdministrador );
		group.add( rdbtnVendedor );

		rdbtnAdministrador.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent arg0 ) {
				NewUser.this.tipoPermissao = Permissao.ADMIN;
			}
		} );
		rdbtnVendedor.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent arg0 ) {
				NewUser.this.tipoPermissao = Permissao.VENDEDOR;
			}
		} );

		super.contentPanel.add( panel );
	}

	private void salvarUser() {
		try {
			final User user = this.parseEntity();
			user.setPermissao( PermissaoController.getInstance().get( this.tipoPermissao ) );

			UserController.getInstance().create( user );
			JOptionPane.showMessageDialog( this, "Usuario cadastrado!" );
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO CADASTRAR USUARIO" );
		}

		this.clear();
	}
}
