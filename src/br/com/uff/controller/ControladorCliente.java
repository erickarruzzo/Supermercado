package br.com.uff.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import br.com.uff.model.domain.entity.cliente.Cliente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.entity.mercado.Venda;
import br.com.uff.model.domain.exceptions.CaixaException;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.Compra;
import br.com.uff.model.domain.valueobject.ItemVenda;
import br.com.uff.model.domain.valueobject.Pagamento;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.model.domain.valueobject.enums.FormaPagamento;
import br.com.uff.model.domain.valueobject.enums.Medida;
import br.com.uff.persitence.Sistema;

public class ControladorCliente extends ControladorGeral{

	/**
	 * Imprime na tela operações para Cliente
	 */
	@Override
	public void printaMenuOpcoes() {
		super.printaMenuOpcoes();
		System.out.println("Digite 2 para selecionar um produto e coloca-lo na sua lista de compras: ");
		System.out.println("Digite 3 para visualizar seu carrinho atual: ");
		System.out.println("Digite 4 para ir ao caixa e finalizar sua compra: ");
	}

	public void iniciaTelaParaCliente(FilialSupermercado filial) {
		int respostaOpcao;
		Cliente cliente = new Cliente();
		List<ItemVenda> carrinhoItensVendaCliente = new LinkedList<ItemVenda>();
		
		do {
			imprimeLinhasEmBranco(5);
			
			imprimeMensagemBemVindoCliente();
			printaMenuOpcoes();
			Scanner tecladoInt = new Scanner(System.in);
			Scanner tecladoString = new Scanner(System.in);
			
			respostaOpcao = tecladoInt.nextInt();
			switch (respostaOpcao) {
			case 0:
				inicializaTelaPrincipal();
				break;
			case 1:
				printaTelaOpcaoVisualizarPrecoProduto(cliente, tecladoString);
				break;
			case 2:
				carrinhoItensVendaCliente.add(printaTelaOpcaoSelecionaProdutoCompra(cliente, 
						tecladoInt, tecladoString));
				break;
			case 3:
				printaTelaCarrinhoCliente(carrinhoItensVendaCliente);
				break;
			case 4:
				printaTelaOpcaoFinalizarCompra(tecladoString, 
						carrinhoItensVendaCliente, filial);
				break;
			default:
				break;
			}
		} while (validaRespostaTelaCliente(respostaOpcao));
	}

	private void imprimeMensagemBemVindoCliente() {
		System.out.println("Bem vindo Cliente");
	}

	/**
	 * @param respostaOpcao
	 * @return
	 */
	private boolean validaRespostaTelaCliente(int respostaOpcao) {
		return (respostaOpcao != 4) && (respostaOpcao != -1);
	}

	
	/**
	 * Opera��o para visualizar o pre�o de um produto
	 * @param cliente
	 * @param tecladoString
	 */
	private void printaTelaOpcaoVisualizarPrecoProduto(Cliente cliente,
			Scanner tecladoString) {
		String produtoNome;
		double preco = 0;
		do {
			try {
				System.out.println("Digite o nome do produto que deseja visualizar: ");
				produtoNome = tecladoString.nextLine();
				preco = cliente.visualizaPrecoProdutoByNome(produtoNome, new Unidade(0));
			} catch (ProdutoException erro) {
				System.out.println(erro.getMessage());
			}
		} while (preco == 0);
		System.out.println("O Pre�o do produto �: " + preco);
	}
	
	/**
	 * Opera��o para selecionar um produto para Compra
	 * @param cliente
	 * @param tecladoInt
	 * @param tecladoString
	 */
	private ItemVenda printaTelaOpcaoSelecionaProdutoCompra(Cliente cliente,
			Scanner tecladoInt, Scanner tecladoString) {
		String produtoNome;
		System.out.println("Digite o nome do produto que deseja comprar: ");
		produtoNome = tecladoString.nextLine();
		Produto produto = Sistema.getProdutoByNome(produtoNome);
		printaLinhaEmBranco();
		int respostaCompra;
		do {
			System.out.println("Digite 1 se deseja compra em Unidades ou Digite 2 se deseja compra em KGs:");
			respostaCompra = tecladoInt.nextInt();
			if(!validaFormaPagamento(respostaCompra)){
				System.out.println("Resposta Inv�lida");
			}
		} while (!validaFormaPagamento(respostaCompra));
		System.out.println("Digite a quantidade: ");
		
		@SuppressWarnings("resource")
		Scanner tecladoDouble = new Scanner(System.in);
		double quantidade = tecladoDouble.nextDouble();
		if(respostaCompra == 1){
			return cliente.selecionaProdutoParaCompra(produto.getId(), new Compra(Medida.UD, quantidade));
		} else{
			return cliente.selecionaProdutoParaCompra(produto.getId(), new Compra(Medida.KG, quantidade));
		}
	}
	
	/**
	 * Opera��o para finalizar a Compra do cliente e se dirigar ao caixa
	 * @param teclado
	 */
	private void printaTelaOpcaoFinalizarCompra(Scanner teclado, List<ItemVenda> carrinhoDeCompras, FilialSupermercado filial) {
		Caixa caixa = escolheCaixaParaCompra(teclado);
		int formaPagamento = escolheFormaPagamento(teclado);
		realizaPagamento(teclado, carrinhoDeCompras, filial, caixa, formaPagamento);
		imprimeLinhasEmBranco(40);
		inicializaTelaPrincipal();;
		
	}

	/**
	 * @param teclado
	 * @param carrinhoDeCompras
	 * @param filial
	 * @param caixa
	 * @param formaPagamento
	 */
	private void realizaPagamento(Scanner teclado, List<ItemVenda> carrinhoDeCompras, FilialSupermercado filial, 
			Caixa caixa, int formaPagamento) {
		
		System.out.println("Digite o valor do seu pagamento: ");
		double valorPagamento = teclado.nextDouble();
		Venda venda;
		if(formaPagamento == 1){
			venda = new Venda(caixa.getFuncionarioResponsavel(), 
					carrinhoDeCompras, new Pagamento(valorPagamento, FormaPagamento.DINHEIRO), filial.getEstoque());
			caixa.finalizaVenda(venda);
		} else{
			venda = new Venda(caixa.getFuncionarioResponsavel(), 
					carrinhoDeCompras, new Pagamento(valorPagamento, FormaPagamento.CARTAO), filial.getEstoque()); 
			caixa.finalizaVenda(venda);
		}
		System.out.println("Venda Realizada com Sucesso!");
		printaLinhaEmBranco();
		System.out.println(venda);
	}

	/**
	 * @param teclado
	 * @return
	 */
	private int escolheFormaPagamento(Scanner teclado) {
		System.out.println("Formas de Pagamento:");
		int formaPagamento;
		do {
			System.out.println("Digite 1 para pagar em Dinheiro ou Digite 2 para pagar em Cart�o:");
			formaPagamento = teclado.nextInt();
			if(!validaFormaPagamento(formaPagamento)){
				System.out.println("Resposta Inv�lida");
			}
		} while (!validaFormaPagamento(formaPagamento));
		return formaPagamento;
	}

	/**
	 * @param teclado
	 * @return
	 */
	private Caixa escolheCaixaParaCompra(Scanner teclado) {
		String numeroCaixa;
		Caixa caixa = null;
		do {
			try {
				System.out.println("Digite o n�mero do caixa que deseja finalizar sua compra: ");
				numeroCaixa = teclado.nextLine();
				caixa = Sistema.getCaixaById(numeroCaixa);
			} catch (CaixaException erro) {
				System.out.println(erro.getMessage());
			}
		} while (caixa == null);
		return caixa;
	}

	/**
	 * Opera��o para imprimir na Tela o carrinho atual do cliente
	 * @param carrinhoItensVendaCliente
	 */
	private void printaTelaCarrinhoCliente(
			List<ItemVenda> carrinhoItensVendaCliente) {
		if(carrinhoItensVendaCliente.isEmpty()){
			System.out.println("Seu carrinho de compras est� vazio");
		} else{
			System.out.println("Carrinho de Compras:");
			for (ItemVenda itemVenda : carrinhoItensVendaCliente) {
				System.out.println(itemVenda);
			}
		}
	}
	
	/**
	 * @param formaPagamento
	 * @return
	 */
	private boolean validaFormaPagamento(int formaPagamento) {
		return formaPagamento == 1 || formaPagamento == 2;
	}
}
