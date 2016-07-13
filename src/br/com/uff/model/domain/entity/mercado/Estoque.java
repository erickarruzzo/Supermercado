package br.com.uff.model.domain.entity.mercado;

import java.util.List;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.exceptions.EstoqueException;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.Compra;
import br.com.uff.model.domain.valueobject.ItemVenda;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.RelatorioEstoque;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.model.domain.valueobject.enums.Medida;
import br.com.uff.persitence.Sistema;

public class Estoque {
	
	private String id;
	private String endereco;
	private int capacidadeItemEstoque;
	private List<ItemEstoque> listaItensEstoque;

	public Estoque(String id, String endereco, int capacidadeItemEstoque,
			List<ItemEstoque> listaItemEstoque) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.capacidadeItemEstoque = capacidadeItemEstoque;
		this.listaItensEstoque = listaItemEstoque;
	}
	
	public List<ItemEstoque> getListaItensEstoque() {
		return listaItensEstoque;
	}

	public void setListaItensEstoque(List<ItemEstoque> listaItensEstoque) {
		this.listaItensEstoque = listaItensEstoque;
	}

	public String getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCapacidadeItemEstoque() {
		return capacidadeItemEstoque;
	}

	public void setCapacidadeItemEstoque(int capacidadeItemEstoque) {
		this.capacidadeItemEstoque = capacidadeItemEstoque;
	}

	public void baixaVendaEstoque(Venda venda) {
		List<ItemVenda> listaItemVenda = venda.getListaVendasUnitarias();
		Estoque estoque = getEstoqueById(venda.getEstoque().getId());
		List<ItemEstoque> listaItemEstoque = estoque.getListaItensEstoque();
		for (ItemVenda itemVenda : listaItemVenda) {
			for (ItemEstoque itemEstoque : listaItemEstoque) {
				if(itemEstoque.getProduto().equals(itemVenda.getProduto())){
					listaItemEstoque.remove(itemEstoque);
					ItemEstoque novoItemEstoque;
					if(itemVenda.getCompra().getMedida().equals(Medida.UD)){
						novoItemEstoque = atualizaQuantidadeUnidadeItemEstoque(itemVenda, itemEstoque);
					} else{
						novoItemEstoque = atualizaPesoQuiloItemEstoque(itemVenda, itemEstoque);
					}
					listaItemEstoque.add(novoItemEstoque);
					break;
				}
			}
		}
	}

	/**
	 * @param itemVenda
	 * @param itemEstoque
	 * @return
	 */
	private ItemEstoque atualizaPesoQuiloItemEstoque(ItemVenda itemVenda,
			ItemEstoque itemEstoque) {
		ItemEstoque novoItemEstoque;
		double pesoEstoque = itemEstoque.getQuilo().getPeso();
		double pesoVenda = itemVenda.getCompra().getQuantidade();
		double novoPeso = pesoEstoque - pesoVenda;
		novoItemEstoque = new ItemEstoque(itemVenda.getProduto(), null, new Quilo(novoPeso));
		return novoItemEstoque;
	}

	/**
	 * @param itemVenda
	 * @param itemEstoque
	 * @return
	 */
	private ItemEstoque atualizaQuantidadeUnidadeItemEstoque(
			ItemVenda itemVenda, ItemEstoque itemEstoque) {
		ItemEstoque novoItemEstoque;
		int quantidadeEstoque = itemEstoque.getUnidade().getQuantidade();
		int quantidadeVenda = (int) itemVenda.getCompra().getQuantidade();
		int novaquantidade = quantidadeEstoque - quantidadeVenda;
		novoItemEstoque = new ItemEstoque(itemVenda.getProduto(), new Unidade(novaquantidade), null);
		return novoItemEstoque;
	}
	
	public static Estoque getEstoqueById(String id){
		for (Estoque estoque : Sistema.getListaEstoque()) {
			if(estoque.getId() == id){
				return estoque;
			}
		}
		throw new EstoqueException(Constantes.ESTOQUE_NAO_EXISTE);
	}
	
	public RelatorioEstoque gerarRelatorioEstoque(Gerente gerente){
		return new RelatorioEstoque(this, gerente);
	}

	/**
	 * Verifica se o produto da compra pode ser vendido
	 * @param produto
	 * @param compra
	 * @return TRUE se o produto pode ser vendido, FALSE caso contrário
	 */
	public boolean verificaPermissaoVenda(Produto produto, Compra compra){
		for (ItemEstoque itemEstoque : listaItensEstoque) {
			if(itemEstoque.getProduto().equals(produto)){
				return verificaQuantidadeProdutoCompra(produto, compra, itemEstoque);
			}
		}
		return false;
	}

	/**
	 * Verifica quantidade do Produto em Estoque 
	 * @param produto
	 * @param compra
	 * @return TRUE se a quantidade em estoque for maior ou igual a quantidade de compra, FALSE caso contrário
	 */
	private boolean verificaQuantidadeProdutoCompra(Produto produto, Compra compra, ItemEstoque itemEstoque) {
		boolean quantidadeEmEstoqueInsuficiente;
		if(compra.getMedida().equals(Medida.KG)){
			quantidadeEmEstoqueInsuficiente = itemEstoque.getQuilo().getPeso() >= compra.getQuantidade() ? false : true;
		} else{
			quantidadeEmEstoqueInsuficiente = itemEstoque.getUnidade().getQuantidade() >= compra.getQuantidade() ? false : true;
		}
		
		if(quantidadeEmEstoqueInsuficiente){
			throw new ProdutoException(Constantes.PRODUTO_QUANTIDADE_INSUFICIENTE_EM_ESTOQUE);
		} else{
			return true;
		}
	}
	
}
