package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Produto;
import controller.ProdutosController;
import exception.ProdutoNotFoundException;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class EditProduto extends FormProduto {
	public EditProduto( final Produto p ) throws ProdutoNotFoundException, TypeNotFoundException {
		super();
		this.setTitle( "ATUALIZAR PRODUTO" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditProduto.this.alterarProduto( p );
			}
		} );

		if ( p != null ) {
			this.updateTextFieldsWithEntity( p );
		}
	}

	private void alterarProduto( final Produto p ) {
		try {
			ProdutosController.getInstance().update( this.update( p ) );
			JOptionPane.showMessageDialog( this, "Produto atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR PRODUTO" );
		}
	}
}
