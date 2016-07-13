package br.com.uff.model.domain.operations;

import br.com.uff.model.domain.valueobject.TipoVenda;


public interface OperacoesGerais {

	/**
	 * Visualiza preco do produto pelo nome
	 * @return
	 */
	public double visualizaPrecoProdutoByNome(String nomeProduto, TipoVenda tipoVenda);
	
	/**
	 * Visualiza preço do produto pelo id
	 * @param idProduto
	 * @return
	 */
	public double visualizaPrecoProdutoById(String idProduto, TipoVenda tipoVenda);
	
	
}
