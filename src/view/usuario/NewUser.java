package view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.UserController;

@SuppressWarnings( "serial" )
public class NewUser extends FormUser {
	public NewUser() throws Exception {
		super();
		this.setTitle( "NOVO USER" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				NewUser.this.salvarUser();
			}
		} );
	}

	private void salvarUser() {
		try {
			UserController.create( this.parseVO() );
			JOptionPane.showMessageDialog( this, "Usuario cadastrado!" );
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO CADASTRAR USUARIO" );
		}

		this.clear();
	}
}
