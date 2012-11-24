package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProdutosController;

@SuppressWarnings( "serial" )
public class NewProduto extends FormProduto {
	public NewProduto() throws Exception {
		super();
		this.setTitle( "NOVO PRODUTO" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				NewProduto.this.salvarProduto();
			}
		} );
	}

	private void salvarProduto() {
		try {
			ProdutosController.create( this.parseVO() );
			JOptionPane.showMessageDialog( this, "Produto cadastrado!" );
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO CADASTRAR PRODUTO" );
		}

		this.clear();
	}
}
