package view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.User;
import controller.UserController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class EditUser extends FormUser {

	public EditUser( final User u ) throws TypeNotFoundException {
		super();
		this.setTitle( "ATUALIZAR USER" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditUser.this.alterarUser( u );
			}
		} );

		if ( u != null ) {
			this.updateTextFieldsWithEntity( u );
		}
	}

	private void alterarUser( final User u ) {
		try {
			UserController.getInstance().update( this.update( u ) );
			JOptionPane.showMessageDialog( this, "User atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR USER" );
		}
	}
}
