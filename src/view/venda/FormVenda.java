package view.venda;

import javax.swing.JPanel;
import javax.swing.JTable;

import model.Venda;
import view.Form;
import dao.VendaDAO;

@SuppressWarnings( "serial" )
public class FormVenda extends Form<Venda> {

	protected Venda			venda;
	private ListaItemVenda	lista;

	public FormVenda( final Venda venda ) throws Exception {
		super( Venda.class, "VENDA" );

		this.initizalize( venda );
	}

	protected void NewVenda() {
		this.lista.NewVenda();
	}

	protected void parseCliente( final Object value, final String field ) {
		super.setValueToField( value, field );
	}

	protected void setVenda( final Venda venda ) {

		try {
			this.initizalize( venda );
			VendaDAO.getInstance().refresh();
		} catch ( final Exception e1 ) {
			e1.printStackTrace();
		}
	}

	private void initizalize( final Venda venda ) throws Exception {
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
