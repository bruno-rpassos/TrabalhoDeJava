package controller;

import java.util.List;

import javax.swing.JDialog;

import model.Venda;
import dao.VendaDAO;
import exception.TypeNotFoundException;
import exception.VendaNotFoundException;

public class VendasController implements Controller<Venda> {
	private static VendasController	instance;

	public static VendasController getInstance() {
		if ( VendasController.instance == null ) VendasController.instance = new VendasController();
		return VendasController.instance;
	}

	private VendasController() {}

	@Override
	public void create( final Venda v ) {
		try {
			VendaDAO.getInstance().saveOrUpdate( v );
		} catch ( final Exception e ) {}
	}

	@Override
	public void edit( final Integer id ) {
		try {
			final Venda venda = VendaDAO.getInstance().getById( id );
			final JDialog view = new view.venda.EditVenda( venda );
			view.setVisible( true );
		} catch ( final VendaNotFoundException e ) {} catch ( final TypeNotFoundException e ) {}
	}

	@Override
	public Venda get( final Integer id ) {
		Venda v = null;
		try {
			v = VendaDAO.getInstance().getById( id );
		} catch ( final VendaNotFoundException e ) {}
		return v;
	}

	@Override
	public List<Venda> getAll() {
		return VendaDAO.getInstance().list();
	}

	@Override
	public void list() {
		try {
			final JDialog view = new view.venda.ListaVenda();
			view.setVisible( true );
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void newResource() {
		try {
			final JDialog view = new view.venda.NewVenda();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update( final Venda v ) {
		try {
			VendaDAO.getInstance().saveOrUpdate( v );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}
}
