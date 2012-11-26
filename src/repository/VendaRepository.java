package repository;

import java.util.ArrayList;
import java.util.List;

import model.Venda;
import exception.VendaNotFoundException;

public class VendaRepository {
	private static Integer			ID	= 1;
	private static VendaRepository	instance;

	public static VendaRepository getInstance() {
		if ( VendaRepository.instance == null ) VendaRepository.instance = new VendaRepository();
		return VendaRepository.instance;
	}

	private final List<Venda>	vendas;

	private VendaRepository() {
		this.vendas = new ArrayList<Venda>();
	}

	public void add( final Venda venda ) throws VendaNotFoundException {
		if ( venda.getId() == null ) venda.setId( new Integer( VendaRepository.ID++ ) );
		else {
			final Venda existente = this.getById( venda.getId() );
			if ( existente != null ) this.vendas.remove( existente );
		}

		this.vendas.add( venda );
	}

	public List<Venda> getAll() {
		return this.vendas;
	}

	public Venda getById( final Integer id ) throws VendaNotFoundException {
		for ( final Venda v : this.vendas )
			if ( v.getId().equals( id ) ) return v;
		throw new VendaNotFoundException();
	}

}
