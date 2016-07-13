package br.com.uff.model.domain.operations;

import java.util.List;

import br.com.uff.model.domain.valueobject.RelatorioEstoque;
import br.com.uff.model.domain.valueobject.RelatorioVenda;

public interface OperacoesGerenciais extends OperacoesEstoque {

	/**
	 * Emitir Relatórios de Vendas por Caixa
	 */
	public RelatorioVenda geraRelatorioVendas(String idCaixa);
	
	/**
	 * Emitir Relatórios de Vendas de todas as Caixas
	 * @return
	 */
	public List<RelatorioVenda> geraListaRelatorioVendas();
	
	/**
	 * Emetir Relatórios por Estoque
	 * @param idEstoque
	 * @return
	 */
	public RelatorioEstoque geraRelatorioEstoque(String idEstoque);
	
}
