package br.com.uff.model.domain.operations;

import br.com.uff.model.domain.entity.mercado.Venda;

public interface OperacoesVenda {

	/**
	 * Realiza a Venda de uma lista de produtos, atualizando o estoque
	 * @param venda
	 */
	public void finalizaVenda(Venda venda);
	
}
