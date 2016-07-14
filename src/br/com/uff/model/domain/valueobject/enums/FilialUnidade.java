package br.com.uff.model.domain.valueobject.enums;

public enum FilialUnidade {

	MEIER("Méier");
	
	private String descricão;

	public String getDescricão() {
		return descricão;
	}

	public void setDescricão(String descricão) {
		this.descricão = descricão;
	}

	private FilialUnidade(String descricão) {
		this.descricão = descricão;
	}
	
}