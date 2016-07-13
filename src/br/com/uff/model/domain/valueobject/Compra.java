package br.com.uff.model.domain.valueobject;

import br.com.uff.model.domain.valueobject.enums.Medida;


public class Compra {

	private Medida medida;
	private double quantidade;
	
	public Compra(Medida medida, double quantidade) {
		super();
		this.medida = medida;
		this.quantidade = quantidade;
	}

	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
}
