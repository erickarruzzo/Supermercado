package br.com.uff.model.domain.valueobject.enums;

public enum Medida {

	KG("Quilo"),
	UD("Unidade");
	
	private String descricao;

	Medida (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
