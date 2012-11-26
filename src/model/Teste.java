package model;

import annotation.Entity;
import annotation.Id;

@Entity
public class Teste extends model.Entity {
	@Id
	private Integer id;
	private String nomeDoCliente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString() {
		return this.getNomeDoCliente();
	}
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}
}
