package br.com.uff.model.domain.valueobject;

public class Quilo extends TipoVenda{
	
	private double peso;

	public Quilo(double peso) {
		super();
		this.peso = peso;
	}
	
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

}
