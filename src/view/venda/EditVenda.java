package view.venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Venda;
import controller.VendasController;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class EditVenda extends FormVenda {
	public EditVenda( final Venda v ) throws TypeNotFoundException {
		super();
		this.setTitle( "ATUALIZAR VENDA" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditVenda.this.alterarVenda( v );
			}
		} );

		if ( v != null ) {
			this.updateTextFieldsWithEntity( v );
		}

		super.setBounds( 100, 100, 450, 400 );
	}

	private void alterarVenda( final Venda v ) {
		try {
			VendasController.getInstance().update( this.update( v ) );
			JOptionPane.showMessageDialog( this, "Venda atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR VENDA" );
		}
	}
}
