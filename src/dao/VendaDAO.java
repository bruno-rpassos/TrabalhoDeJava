package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Venda;
import exception.MissingAnnotationException;
import exception.SQLException;

public class VendaDAO extends AbstractDAO<Venda> {
	private static VendaDAO	instance;

	public static VendaDAO getInstance() throws SQLException {
		if ( VendaDAO.instance == null ) {
			try {
				VendaDAO.instance = new VendaDAO();
			} catch (MissingAnnotationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return VendaDAO.instance;
	}

	private final List<Listener>	listeners;

	private VendaDAO() throws MissingAnnotationException, ClassNotFoundException, SQLException {
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
	public void saveOrUpdate( final Venda venda ) throws SQLException {
		super.saveOrUpdate(venda);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}

}
