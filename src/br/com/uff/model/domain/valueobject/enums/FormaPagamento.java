package br.com.uff.model.domain.valueobject.enums;

public enum FormaPagamento {

	DINHEIRO(0),
	CARTÃO(1);
	
	private int forma;

	FormaPagamento(int forma) {
		this.forma = forma;
	}

	public int getForma() {
		return forma;
	}

	public void setForma(int forma) {
		this.forma = forma;
	}
	
	
	
}
