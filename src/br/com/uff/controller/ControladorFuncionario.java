package br.com.uff.controller;

import java.util.List;
import java.util.Scanner;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.usuario.Usuario;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.persitence.Sistema;

public class ControladorFuncionario extends ControladorGeral {

	@Override
	public void printaMenuOpcoes(){
		super.printaMenuOpcoes();
		System.out.println("Digite 2 para verificar se está alocado em algum caixa: ");
	}

	public void iniciaTelaParaFuncionario(Usuario usuario, FilialSupermercado filial) {
		Funcionario funcionario = Sistema.getFuncionarioByLogin(usuario.getLogin());
		int respostaOpcao;
		
		do {
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			printaLinhaEmBranco();
			
			imprimeMensagemBemVindoFuncionario(funcionario);
			printaMenuOpcoes();
			@SuppressWarnings("resource")
			Scanner tecladoInt = new Scanner(System.in);
			Scanner tecladoString = new Scanner(System.in);
			
			try {
				respostaOpcao = tecladoInt.nextInt();
			} catch (Exception e) {
				System.out.println("Resposta inválida, digite novamente.");
				respostaOpcao = 10;
				tecladoInt.nextLine();
			}
			
			switch (respostaOpcao) {
				case 0:
					inicializaTelaPrincipal();
					break;
				case 1:
					printaTelaOpcaoVisualizarPrecoProduto(funcionario, tecladoString);
					break;
				case 2:
					printaTelaOpcaoVerificaAlocacao(funcionario, filial);
					break;
				default:
					break;
			}
			
		} while (validaRespostaFuncionario(respostaOpcao));
	}

	private void imprimeMensagemBemVindoFuncionario(Funcionario funcionario) {
		System.out.println("Bem vindo Funcionário " + funcionario.getNome());
	}

	private void printaTelaOpcaoVerificaAlocacao(Funcionario funcionario, FilialSupermercado filial) {
		List<Caixa> listaCaixaMercados = filial.getListaCaixas();
		boolean isAlocado = false;
		Caixa caixaAlocado = null;
		for (Caixa caixa : listaCaixaMercados) {
			if(caixa.getFuncionarioResponsavel().getNome().equals(funcionario.getNome())){
				isAlocado = true;
				caixaAlocado = caixa;
			} 
		}		
		if(isAlocado){
			System.out.println("Você está alocado no caixa " + caixaAlocado.getId());
		} else{
			System.out.println("Você não está alocado a nenhum caixa agora");
		}
	}

	/**
	 * @param respostaOpcao
	 * @return
	 */
	private boolean validaRespostaFuncionario(int respostaOpcao) {
		return (respostaOpcao != -1);
	}
	
	/**
	 * Operação para visualizar o preço de um produto
	 * @param cliente
	 * @param tecladoString
	 */
	private void printaTelaOpcaoVisualizarPrecoProduto(Funcionario funcionario,
			Scanner tecladoString) {
		String produtoNome;
		double preco = 0;
		do {
			try {
				System.out.println("Digite o nome do produto que deseja visualizar: ");
				produtoNome = tecladoString.nextLine();
				preco = funcionario.visualizaPrecoProdutoByNome(produtoNome);
			} catch (ProdutoException erro) {
				System.out.println(erro.getMessage());
			}
		} while (preco == 0);
		System.out.println("O Preço do produto é: " + preco);
	}
	
	
}
