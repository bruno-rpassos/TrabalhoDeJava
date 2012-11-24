package view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.vo.UserVO;
import controller.UserController;

@SuppressWarnings( "serial" )
public class EditUser extends FormUser {

	public EditUser( final UserVO vo ) throws Exception {
		super();
		this.setTitle( "ATUALIZAR USER" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditUser.this.alterarUser( vo );
			}
		} );

		if ( vo != null ) {
			this.updateTextFieldsWithVO( vo );
		}
	}

	private void alterarUser( final UserVO vo ) {
		try {
			UserController.update( this.update( vo ) );
			JOptionPane.showMessageDialog( this, "User atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR USER" );
		}
	}
}
