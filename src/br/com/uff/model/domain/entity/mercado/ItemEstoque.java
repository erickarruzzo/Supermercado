package br.com.uff.model.domain.entity.mercado;

import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.Unidade;

public class ItemEstoque {

	private Produto produto;
	private Unidade unidade;
	private Quilo quilo;

	public ItemEstoque(Produto produto, Unidade unidade, Quilo quilo) {
		super();
		this.produto = produto;
		this.unidade = unidade;
		this.quilo = quilo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Quilo getQuilo() {
		return quilo;
	}

	public void setQuilo(Quilo quilo) {
		this.quilo = quilo;
	}

	@Override
	public String toString() {
		String retornoString = produto.toString() + " ";
		if(unidade != null){
			retornoString += "," + unidade.getQuantidade() + " Unidades";
		} else{
			retornoString += "," + quilo.getPeso() + " Kgs";
		}
		return retornoString;
	}
	
	
}
