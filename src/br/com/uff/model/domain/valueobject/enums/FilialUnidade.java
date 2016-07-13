package br.com.uff.model.domain.valueobject.enums;

public enum FilialUnidade {

	MEIER("Meier"), COPACABANA("Copacabana");
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private FilialUnidade(String descricao) {
		this.descricao = descricao;
	}
	
}