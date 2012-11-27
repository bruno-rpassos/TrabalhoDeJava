package view.venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Produto;
import model.Venda;
import controller.Sessao;
import controller.VendasController;
import exception.PermissaoNegadaException;

@SuppressWarnings( "serial" )
public class EditVenda extends FormVenda {
	public EditVenda( final Venda v ) throws Exception {
		super( v );
		try {
			Sessao.getInstance().temPermissaoEditarVenda();
		} catch ( final PermissaoNegadaException e1 ) {
			this.dispose();
		}

		this.setTitle( "ATUALIZAR VENDA" );
		this.saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				EditVenda.this.alterarVenda( v );
			}
		} );

		if ( v != null ) {
			this.updateTextFieldsWithEntity( v );
			this.parseCliente( v.getCliente().getNome(), "cliente" );
		}
	}

	private void alterarVenda( final Venda v ) {
		try {
			System.out.println( "updating venda" );
			System.out.println( "   > id         " + v.getId() );
			System.out.println( "   > descricao  " + v.getDescricao() );
			System.out.println( "   > valortotal " + v.getValorTotal() );
			System.out.println( "   > desconto   " + v.getValorTotal() );
			System.out.println( "   > produtos" );
			for ( final Produto p : v.getProdutos() ) {
				System.out.println( "       >  id        " + p.getId() );
				System.out.println( "       >  descricao " + p.getDescricao() );
				System.out.println( "       >  quantid   " + p.getQuantidade() );
			}

			final Venda atualizada = this.update( v );
			atualizada.setProdutos( v.getProdutos() );
			atualizada.setValorTotal( v.getValorTotal() );
			atualizada.setCliente( v.getCliente() );

			VendasController.getInstance().update( atualizada );
			JOptionPane.showMessageDialog( this, "Venda atualizado!" );
			this.dispose();
		} catch ( final Exception ex ) {
			JOptionPane.showMessageDialog( this, ">> ERRO AO ATUALIZAR VENDA" );
		}
	}
}
