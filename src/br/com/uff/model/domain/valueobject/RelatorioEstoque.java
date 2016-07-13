package br.com.uff.model.domain.valueobject;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.ItemEstoque;

public class RelatorioEstoque extends Relatorio{

	private Estoque estoque;
	
	public RelatorioEstoque(Estoque estoque, Gerente gerente) {
		this.estoque = estoque;
		this.funcionario = gerente;
		this.dataRelatorio = new Date();
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public void imprimeRelatorioEstoque(){
		List<String> listaRelatorioString = new LinkedList<String>();
		
		String relatorioItemEstoque;
		for (ItemEstoque itemEstoque : estoque.getListaItensEstoque()) {
			relatorioItemEstoque = "Produto : " + itemEstoque.getProduto().toString();
			if(itemEstoque.getUnidade() != null){
				relatorioItemEstoque += "Quantidade :" + itemEstoque.getUnidade().getQuantidade();
			} else{
				relatorioItemEstoque += "Peso :" + itemEstoque.getQuilo().getPeso();
			}
			listaRelatorioString.add(relatorioItemEstoque);
		}
		
		System.out.println("Relatório de Estoque " + estoque.getId());
	
		for (String stringRelatorioEstoque : listaRelatorioString) {
			System.out.println(stringRelatorioEstoque);
		}
		
		System.out.println("Relatório emitido por: " + funcionario);
		System.out.println("Data Relatório Emitido: " + dataRelatorio);
		
	}

}
