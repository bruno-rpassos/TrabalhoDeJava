package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Venda;

public class VendaDAO extends AbstractDAO<Venda> {
	private static VendaDAO	instance;

	public static VendaDAO getInstance() throws Exception {
		if ( VendaDAO.instance == null ) {
			VendaDAO.instance = new VendaDAO();
		}
		return VendaDAO.instance;
	}

	private final List<Listener>	listeners;

	private VendaDAO() throws Exception {
		super(Venda.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	public void refresh() {
		this.dataChanged();
	}

	@Override
	public void saveOrUpdate( final Venda venda ) throws Exception {
		super.saveOrUpdate(venda);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
