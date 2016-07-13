package br.com.uff.model.domain.operations;

import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.Unidade;

public interface OperacoesEstoque {

	/**
	 * adiciona um produto em estoque
	 * @param produto
	 * @param estoque
	 */
	public void adicionaProdutoNoEstoque(Produto produto, String idEstoque, Unidade unidade, Quilo quilo);
	
	/**
	 * remove produto do estoque
	 * @param produto
	 */
	public void baixaEstoque(Produto produto, String idEstoque);
	
}
