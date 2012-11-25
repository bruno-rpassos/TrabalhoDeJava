package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.Venda;
import repository.VendaRepository;
import exception.VendaNotFoundException;

public class VendaDAO implements DAO<Venda> {
	private static VendaDAO	instance;

	public static VendaDAO getInstance() {
		if ( VendaDAO.instance == null ) {
			VendaDAO.instance = new VendaDAO();
		}
		return VendaDAO.instance;
	}

	private final List<Listener>	listeners;

	private VendaDAO() {
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	@Override
	public Venda getById( final Integer id ) throws VendaNotFoundException {
		return VendaRepository.getInstance().getById( id );
	}

	@Override
	public List<Venda> list() {
		return VendaRepository.getInstance().getAll();
	}

	public void refresh() {
		this.dataChanged();
	}

	@Override
	public void saveOrUpdate( final Venda venda ) throws VendaNotFoundException {
		VendaRepository.getInstance().add( venda );
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners ) {
			l.actionPerformed();
		}
	}

}
