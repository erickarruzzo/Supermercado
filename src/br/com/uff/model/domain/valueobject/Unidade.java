package br.com.uff.model.domain.valueobject;

public class Unidade extends TipoVenda{

	private int quantidade;
	
	public Unidade(int quantidade) {
		super();
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
}
