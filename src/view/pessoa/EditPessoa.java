package view.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Pessoa;
import controller.PessoaController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class EditPessoa extends FormPessoa {
	public EditPessoa( final Pessoa p ) throws TypeNotFoundException {
		super();
		this.setTitle( "ATUALIZAR CLIENTE" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditPessoa.this.alterarPessoa( p );
			}
		} );

		if ( p != null ) this.updateTextFieldsWithEntity( p );
	}

	private void alterarPessoa( final Pessoa p ) {
		try {
			PessoaController.getInstance().update( this.update( p ) );
			JOptionPane.showMessageDialog( this, "Cliente atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR CLIENTE" );
		}
	}
}
