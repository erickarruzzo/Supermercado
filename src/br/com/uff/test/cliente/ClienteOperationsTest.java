package br.com.uff.test.cliente;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.uff.model.domain.entity.cliente.Cliente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.mercado.Venda;
import br.com.uff.model.domain.valueobject.Compra;
import br.com.uff.model.domain.valueobject.ItemVenda;
import br.com.uff.model.domain.valueobject.Pagamento;
import br.com.uff.model.domain.valueobject.enums.FormaPagamento;
import br.com.uff.model.domain.valueobject.enums.Medida;
import br.com.uff.persitence.Sistema;

public class ClienteOperationsTest {

	private Caixa caixa = Sistema.getListaCaixaMercado().get(1);
	private FilialSupermercado filial = Sistema.getListaFilialSupermercado().get(0);
	
	/**
	 * Teste uma compra feito pelo Cliente em Cartão
	 * Produtos comprados:
	 * 10 Caixas de Leite
	 * 7 KGs de Alcatra
	 * 2,5 KGs de Feijão
	 * 
	 */
	@Test
	public void RealizaCompraCartaoTest(){
		Cliente cliente = new Cliente();
		List<ItemVenda> listaItemVenda = new LinkedList<ItemVenda>();
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("1", new Compra(Medida.UD, 10)));
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("2", new Compra(Medida.KG, 7)));
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("4", new Compra(Medida.KG, 2.5)));
		double valorPagamento = 126.275;
		Pagamento pagamento = new Pagamento(valorPagamento, FormaPagamento.CARTÃO);
		Venda venda = new Venda(caixa.getFuncionarioResponsavel(), 
								listaItemVenda, pagamento, filial.getEstoque());
		caixa.finalizaVenda(venda);
		Assert.assertEquals(venda.getValorVendaFinal(), valorPagamento, 0.01);
	}
	
	/**
	 * Teste para verificar o troco
	 * Produtos comprados:
	 * 10 Caixas de Leite
	 * 7 KGs de Alcatra
	 * 2,5 KGs de Feijão
	 * 
	 */
	@Test
	public void RealizaCompraDinheiroTrocoTest(){
		Cliente cliente = new Cliente();
		List<ItemVenda> listaItemVenda = new LinkedList<ItemVenda>();
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("1", new Compra(Medida.UD, 10)));
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("2", new Compra(Medida.KG, 7)));
		listaItemVenda.add(cliente.selecionaProdutoParaCompra("4", new Compra(Medida.KG, 2.5)));
		double valorPagamento = 140;
		double troco = 13.725;
		Pagamento pagamento = new Pagamento(valorPagamento, FormaPagamento.DINHEIRO);
		Venda venda = new Venda(caixa.getFuncionarioResponsavel(), 
								listaItemVenda, pagamento, filial.getEstoque());
		caixa.finalizaVenda(venda);
		Assert.assertEquals(venda.getPagamento().getTroco(), troco, 0.01);
	}
	
}
