package view.venda;

import javax.swing.JPanel;
import javax.swing.JTable;

import model.Venda;
import view.Form;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class FormVenda extends Form<Venda> {

	protected Venda			venda;
	private ListaItemVenda	lista;

	public FormVenda( final Venda venda ) throws TypeNotFoundException {
		super( Venda.class, "VENDA" );
		super.setBounds( 100, 100, 450, 400 );

		this.initizalize( venda );
	}

	protected void NewVenda() {
		this.lista.NewVenda();
	}

	private void initizalize( final Venda venda ) {
		try {
			this.venda = venda;
			this.lista = new view.venda.ListaItemVenda( venda );

			final JTable table = new JTable( this.lista );

			final JPanel panel = new JPanel();
			panel.add( table );

			super.contentPanel.add( panel );

		} catch ( final InstantiationException e ) {} catch ( final IllegalAccessException e ) {}
	}
}
