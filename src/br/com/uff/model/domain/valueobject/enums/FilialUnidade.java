package br.com.uff.model.domain.valueobject.enums;

public enum FilialUnidade {

	MEIER("M�ier");
	
	private String descric�o;

	public String getDescric�o() {
		return descric�o;
	}

	public void setDescric�o(String descric�o) {
		this.descric�o = descric�o;
	}

	private FilialUnidade(String descric�o) {
		this.descric�o = descric�o;
	}
	
}