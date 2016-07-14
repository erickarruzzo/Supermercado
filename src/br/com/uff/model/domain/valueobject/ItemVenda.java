package br.com.uff.model.domain.valueobject;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.enums.Medida;

public class ItemVenda {

	private Produto produto;
	private Compra compra;
	
	public ItemVenda(Produto produto, Compra compra) {
		super();
		this.produto = produto;
		this.compra = compra;
	}

	public double calculaValorVenda(Estoque estoque){
		if(estoque.verificaPermissaoVenda(produto, compra)){
			if(compra.getMedida().equals(Medida.UD)){
				return compra.getQuantidade() * produto.getPrecoUnitario();
			} else{
				return compra.getQuantidade() * produto.getPrecoQuilo();
			}
		}
		
		throw new ProdutoException(Constantes.PRODUTO_NAO_ESTA_EM_NENHUM_ESTOQUE);
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("Dados do produto: ");
		stringBuffer.append("Nome: ").append(produto.getNome()).append(", ");
		stringBuffer.append("Marca: ").append(produto.getMarca()).append(", ");
		stringBuffer.append("Tipo do Produto: ").append(produto.getTipoProduto()).append(", ");
		stringBuffer.append("Quantidade: ").append(compra.getQuantidade()).append(", ");
		stringBuffer.append("Preço: ").append(verificaPrecoProduto(compra));
		return stringBuffer.toString();
	}

	private double verificaPrecoProduto(Compra compra) {
		if(compra.getMedida().equals(Medida.KG)){
			return produto.getPrecoQuilo();
		} else{
			return produto.getPrecoUnitario();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	
	
}
