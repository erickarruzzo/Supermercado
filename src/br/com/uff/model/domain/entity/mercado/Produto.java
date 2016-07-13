package br.com.uff.model.domain.entity.mercado;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.enums.TipoProduto;
import br.com.uff.persitence.Sistema;

/**
 * Classe Produto
 * @author lucas.fernandes
 *
 */
public class Produto {

	private String id;
	private String nome;
	private String marca;
	private TipoProduto tipoProduto;
	private double precoUnitario;
	private double precoQuilo;

	public Produto(String id, String nome, String marca,
			TipoProduto tipoProduto, double precoUnitario, double precoQuilo) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.tipoProduto = tipoProduto;
		this.precoUnitario = precoUnitario;
		this.precoQuilo = precoQuilo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	public static Produto getProdutoById(String id){
		for (Produto produto : Sistema.getListaProduto()) {
			if(produto.getId() == id){
				return produto;
			}
		}
		throw new ProdutoException(Constantes.PRODUTO_NAO_EXISTE);
	}
	
	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getPrecoQuilo() {
		return precoQuilo;
	}

	public void setPrecoQuilo(double precoQuilo) {
		this.precoQuilo = precoQuilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoQuilo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precoUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((tipoProduto == null) ? 0 : tipoProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Produto){
			Produto produtoClone = (Produto) obj;
			return produtoClone.getId() == this.getId() ? true : false;
		} else{
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer produtoStringBuffer = new StringBuffer();
		produtoStringBuffer.append("ID: " +  id + ", ");
		produtoStringBuffer.append("Nome: " +  nome + ", ");
		produtoStringBuffer.append("Marca: " +  marca + ", ");
		produtoStringBuffer.append("Tipo: " + tipoProduto.getDescricao() + ", ");
		if(precoQuilo == 0){
			produtoStringBuffer.append("Preço Unitário:  " +  "R$" + precoUnitario);
		} else{
			produtoStringBuffer.append(", " + "Preço Quilo:  " +  "R$" + precoQuilo + " ");
		}
		return produtoStringBuffer.toString();
	}
	
}
