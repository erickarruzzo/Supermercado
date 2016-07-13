package br.com.uff.model.domain.valueobject.enums;

public enum TipoProduto {

	ALIMENTO("Alimento"),
	BEBIDA("Bebida"),
	CARNE("Carne"),
	LIMPEZA("Limpeza");
	
	private String descricao;
	
	TipoProduto(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
