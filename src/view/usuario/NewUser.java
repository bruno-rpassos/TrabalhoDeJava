package view.usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Permissao;
import model.User;
import controller.PermissaoController;
import controller.Sessao;
import controller.UserController;
import exception.PermissaoNegadaException;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class NewUser extends FormUser {

	private Integer	tipoPermissao	= Permissao.VENDEDOR;

	public NewUser() throws TypeNotFoundException {
		super();
		try {
			Sessao.getInstance().temPermissaoCriarUser();
			this.setVisible( true );
		} catch ( final PermissaoNegadaException e1 ) {
			this.dispose();
		}

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
		final JRadioButton rdbtnAdministrador = new JRadioButton( "Administrador" );
		final JRadioButton rdbtnVendedor = new JRadioButton( "Vendedor", true );

		final ButtonGroup group = new ButtonGroup();
		group.add( rdbtnAdministrador );
		group.add( rdbtnVendedor );

		final JPanel panel = new JPanel();

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

		this.getContentPane().add( panel, BorderLayout.NORTH );
		final GroupLayout gl_panel = new GroupLayout( panel );
		gl_panel.setHorizontalGroup( gl_panel.createParallelGroup( Alignment.LEADING ).addGroup(
						gl_panel.createSequentialGroup().addGap( 116 ).addComponent( rdbtnAdministrador ).addGap( 6 ).addComponent( rdbtnVendedor ) ) );
		gl_panel.setVerticalGroup( gl_panel.createParallelGroup( Alignment.LEADING ).addGroup(
						gl_panel.createSequentialGroup()
										.addGap( 5 )
										.addGroup( gl_panel.createParallelGroup( Alignment.LEADING ).addComponent( rdbtnAdministrador )
														.addComponent( rdbtnVendedor ) ) ) );
		panel.setLayout( gl_panel );
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
