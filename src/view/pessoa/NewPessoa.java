package view.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.PessoaController;

@SuppressWarnings( "serial" )
public class NewPessoa extends FormPessoa {
	public NewPessoa() throws Exception {
		super();
		this.setTitle( "NOVO CLIENTE" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				NewPessoa.this.salvarPessoa();
			}
		} );
	}

	private void salvarPessoa() {
		try {
			PessoaController.getInstance().create( this.parseEntity() );
			JOptionPane.showMessageDialog( this, "Cliente cadastrado!" );
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO CADASTRAR CLIENTE" );
		}

		this.clear();
	}
}
