package br.com.uff.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.mercado.ItemEstoque;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.entity.usuario.Usuario;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.RelatorioEstoque;
import br.com.uff.model.domain.valueobject.RelatorioVenda;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.model.domain.valueobject.enums.TipoProduto;
import br.com.uff.persitence.Sistema;

public class ControladorGerente extends ControladorGeral {

	/**
	 * Imprime na tela opera��es para Gerente
	 */
	@Override
	public void printaMenuOpcoes() {
		super.printaMenuOpcoes();
		System.out.println("Digite 2 para alocar um funcion�rio em um caixa: ");
		System.out.println("Digite 3 para visualizar quais funcion�rios est�o alocados nos caixas");
		System.out.println("Digite 4 para adicionar um produto no estoque: ");
		System.out.println("Digite 5 para visualiar produtos no estoque: ");
		System.out.println("Digite 6 para gera um relat�rio de vendas de um caixa espec�fico: ");
		System.out.println("Digite 7 para gera uma lista de relat�rios de vendas de todo os caixas: ");
		System.out.println("Digite 8 para gera um relat�rio do estoque da sua filial: ");
	}
	
	public void iniciaTelaParaGerente(Usuario usuario, FilialSupermercado filial) {
		Gerente gerente= Sistema.getGerenteByLogin(usuario.getLogin());
		int respostaOpcao;
		do {
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			
			imprimeMensagemBemVindoGerente(gerente);
			printaMenuOpcoes();
			@SuppressWarnings("resource")
			Scanner tecladoInt = new Scanner(System.in);
			Scanner tecladoString = new Scanner(System.in);
			
			try{
				respostaOpcao = tecladoInt.nextInt();
			}catch (InputMismatchException ex){
				System.out.println("Resposta inv�lida, digite novamente.");
				respostaOpcao = 10;
				tecladoInt.nextLine();
			}
			
			switch (respostaOpcao) {
			case 0:
				inicializaTelaPrincipal();
				break;
			case 1:
				printaTelaOpcaoVisualizarPrecoProduto(gerente, tecladoString);
				break;
			case 2:
				printaTelaOpcaoAlocaFuncionarioCaixa(gerente);
				break;
			case 3:
				printaTelaOpcaoVisualizaAlocacaoCaixas();
				break;
			case 4:
				printaTelaOpcaoAdicionaProdutoEstoque(gerente, filial);
				break;
			case 5:
				printaTelaOpcaoVisualizaProdutosNoEstoque(gerente, filial);
				break;
			case 6:
				printaTelaOpcaoGeraRelatorioPorCaixa(gerente, filial);
				break;
			case 7:
				printaTelaOpcaoGeraRelatorioTodosCaixas(gerente, filial);
				break;
			case 8:
				printaTelaOpcaoGeraRelatorioEstoquePorFilial(gerente, filial);
				break;
			default:
				break;
			}
			
		} while (validaRespostaGerente(respostaOpcao));
	}

	/**
	 * @param respostaOpcao
	 * @return
	 */
	private boolean validaRespostaGerente(int respostaOpcao) {
		return respostaOpcao != -1;
	}

	private void printaTelaOpcaoGeraRelatorioEstoquePorFilial(Gerente gerente, FilialSupermercado filial) {
		RelatorioEstoque relatorioEstoque = gerente.geraRelatorioEstoque(filial.getEstoque().getId());
		relatorioEstoque.imprimeRelatorioEstoque();
	}

	private void printaTelaOpcaoGeraRelatorioTodosCaixas(Gerente gerente, FilialSupermercado filial) {
		List<RelatorioVenda> listaRelatorioVenda = gerente.geraListaRelatorioVendas();
		for (RelatorioVenda relatorioVenda : listaRelatorioVenda) {
			System.out.println(relatorioVenda);
		}
	}

	private void printaTelaOpcaoGeraRelatorioPorCaixa(Gerente gerente, FilialSupermercado filial) {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o n�mero do caixa que deseja gerar relat�rio de vendas: ");
		String idCaixa = teclado.nextLine();
		boolean idCaixaValido = true;
		do {
			try {
				//Valida entrada do usu�rio
				@SuppressWarnings("unused")
				Caixa caixa = Sistema.getCaixaById(idCaixa);
			} catch (RuntimeException erro) {
				erro.getMessage();
				idCaixaValido = false;
			}
		} while (!(idCaixaValido));
		
		RelatorioVenda relatorioVenda = gerente.geraRelatorioVendas(idCaixa);
		System.out.println(relatorioVenda);
	}

	private void printaTelaOpcaoVisualizaProdutosNoEstoque(Gerente gerente, FilialSupermercado filial) {
		Estoque estoque = filial.getEstoque();
		for (ItemEstoque itemEstoque : estoque.getListaItensEstoque()) {
			System.out.println(itemEstoque);
		}
		
	}

	private void printaTelaOpcaoAdicionaProdutoEstoque(Gerente gerente, FilialSupermercado filial) {
		Scanner teclado = new Scanner(System.in);
		String nome, marca, id;
		double precoUnitario, precoQuilo;
		precoUnitario = precoQuilo = 0;
		id = geraIdAleatorio();
		System.out.println("Digite o nome do produto que deseja adicionar no estoque: ");
		nome = teclado.nextLine();
		System.out.println("Digite o nome da marca do produto: ");
		marca = teclado.nextLine();
		List<TipoProduto> listaTipoProdutos = Sistema.getListaTiposProdutosMercado();
		TipoProduto tipoProduto = capturaTipoProdutodoUsuario(teclado, listaTipoProdutos);

		System.out.println("Digite o preco em unidades do produto: "
				+ "(Caso o produto n�o seja vendido em Unidades, digite 0)");
		precoUnitario = teclado.nextDouble();
		System.out.println("Digite o preco em Quilo do produto: "
				+ "(Caso o produto n�o seja vendido em Quilo, digite 0)");
		precoQuilo = teclado.nextDouble();
		
		Produto produto = new Produto(id, nome, marca, tipoProduto, precoUnitario, precoQuilo);
		
		Unidade unidade = null;
		Quilo quilo = null;
		
		System.out.println("Digite a quantidade do produto (Unidades) que deseja adicionar: "
				+ "(Caso o produto n�o seja vendido em Unidades, digite 0)");
		int quantidadeProduto = teclado.nextInt();
		if(!(quantidadeProduto == 0)){
			unidade = new Unidade(quantidadeProduto);
		}
		
		System.out.println("Digite o peso do produto que deseja adicionar: "
				+ "(Caso o produto n�o seja vendido em Quilo, digite 0");
		double pesoProduto= teclado.nextInt();
		if(!(pesoProduto == 0)){
			quilo = new Quilo(pesoProduto);
		}
		
		gerente.adicionaProdutoNoEstoque(produto, filial.getEstoque().getId(), unidade, quilo);
	}

	/**
	 * Gera id aleat�rio
	 * @return
	 */
	private String geraIdAleatorio() {
		Random random = new Random();
		int idInt = random.nextInt(100) + 1;
		return Integer.toString(idInt);
	}

	/**
	 * @param teclado
	 * @param listaTipoProdutos
	 */
	private TipoProduto capturaTipoProdutodoUsuario(Scanner teclado,
			List<TipoProduto> listaTipoProdutos) {
		String tipoProduto;
		boolean existeTipoProduto = false;
		do {
			System.out.println("Existem " + listaTipoProdutos.size() + " tipos de produtos no Mercado");
			System.out.println("Qual � a categoria do seu produto? ");
			for (TipoProduto tipoProdutoLista : listaTipoProdutos) {
				System.out.println(tipoProdutoLista.getDescricao());
			}
			tipoProduto = teclado.nextLine();
			for (TipoProduto tipoProdutoLista : listaTipoProdutos) {
				if(tipoProduto.equals(tipoProdutoLista.getDescricao())){
					existeTipoProduto = true;
					return tipoProdutoLista;
				}
			}
			System.out.println("N�o pode ser adicionado esse tipo de produto!");
		} while (!existeTipoProduto);
		return null;
	}

	private void printaTelaOpcaoVisualizaAlocacaoCaixas() {
		for (Caixa caixa: Sistema.getListaCaixaMercado()) {
			System.out.println("Caixa " + caixa.getId() + ": " + caixa.getFuncionarioResponsavel());
		}
		
	}

	private void printaTelaOpcaoAlocaFuncionarioCaixa(Gerente gerente) {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		String nomeFuncionario, idCaixa;
		System.out.println("Digite o nome do funcionario que deseja alocar: ");
		nomeFuncionario = teclado.nextLine();
		System.out.println("Digite o n�mero do caixa que deseja alocar o funcion�rio: ");
		idCaixa = teclado.nextLine();
		Funcionario funcionario = null;
		Caixa caixa = null;
		
		try {
			funcionario = Sistema.getFuncionarioByName(nomeFuncionario);
			caixa = Sistema.getCaixaById(idCaixa);
		} catch (RuntimeException erro) {
			System.out.println(erro.getMessage());
		}
		
		gerente.alocaFuncionarioNoCaixa(caixa, funcionario);
	}

	/**
	 * Opera��o para visualizar o pre�o de um produto
	 * @param cliente
	 * @param tecladoString
	 */
	private void printaTelaOpcaoVisualizarPrecoProduto(Gerente gerente,
			Scanner tecladoString) {
		String produtoNome;
		double preco = 0;
		do {
			try {
				System.out.println("Digite o nome do produto que deseja visualizar: ");
				produtoNome = tecladoString.nextLine();
				preco = gerente.visualizaPrecoProdutoByNome(produtoNome);
			} catch (ProdutoException erro) {
				System.out.println(erro.getMessage());
			}
		} while (preco == 0);
		System.out.println("O Pre�o do produto �: " + preco);
	}
	
	/**
	 * @param gerente
	 */
	protected void imprimeMensagemBemVindoGerente(Gerente gerente) {
		System.out.println("Bem vindo Gerente " + gerente.getNome());
	}
	
}
