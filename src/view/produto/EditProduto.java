package view.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.vo.ProdutoVO;
import controller.ProdutosController;

@SuppressWarnings( "serial" )
public class EditProduto extends FormProduto {
	public EditProduto( final ProdutoVO vo ) throws Exception {
		super();
		this.setTitle( "ATUALIZAR PRODUTO" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditProduto.this.alterarProduto( vo );
			}
		} );

		if ( vo != null ) {
			this.updateTextFieldsWithVO( vo );
		}
	}

	private void alterarProduto( final ProdutoVO vo ) {
		try {
			ProdutosController.update( this.update( vo ) );
			JOptionPane.showMessageDialog( this, "Produto atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR PRODUTO" );
		}
	}
}
