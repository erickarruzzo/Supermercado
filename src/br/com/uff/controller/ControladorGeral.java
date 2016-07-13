package br.com.uff.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.usuario.Usuario;
import br.com.uff.persitence.Sistema;

public class ControladorGeral {

	/**
	 * Inicia Tela de Login
	 */
	public void inicializaTelaPrincipal(){
			int resposta;
			Scanner teclado = new Scanner(System.in);
			FilialSupermercado filial = Sistema.getListaFilialSupermercado().get(0);
			inicializaConfiguracoesIniciais();
			
			do {
				try{
					System.out.println("Digite 1 para utilizar o sistema como cliente ou 2 para utilizar como funcionário:");
					resposta = teclado.nextInt();
					
				} catch (InputMismatchException ex){
					resposta=0; //recebendo um valor inteiro inválido como opção
					teclado.nextLine(); //esvaziando buffer
				}
				
				if(resposta == 1){
					ControladorCliente controllerCliente = new ControladorCliente();
					controllerCliente.iniciaTelaParaCliente(filial);
				} else if(resposta == 2){
					Usuario usuario = inicializaTelaLoginFuncionario();
					direcionaUsuario(filial, usuario);
				} else{
					System.out.println("Opçãoo inválida!");
					printaLinhaEmBranco();
				}
							
			} while (!validaRespostaTelaInicial(resposta));
			teclado.close();
	}
	
	protected void printaMenuOpcoes(){
		System.out.println("Digite -1 para sair do Sistema: ");
		System.out.println("Digite 0 para fazer logoff e entrar como outro Usuário");
		System.out.println("Digite 1 para visualizar o preço de um produto: ");
	}

	public void imprimeLinhasEmBranco(int quantidade){
		for (int i = 0; i < quantidade; i++) {
			printaLinhaEmBranco();
		}
	}
	
	/**
	 * @param filial
	 * @param usuario
	 */
	private void direcionaUsuario(FilialSupermercado filial,
			Usuario usuario) {
		if(usuario instanceof Funcionario){
			ControladorFuncionario controllerFuncionario = new ControladorFuncionario();
			controllerFuncionario.iniciaTelaParaFuncionario(usuario, filial);
		} else{
			ControladorGerente controllerGerente = new ControladorGerente();
			controllerGerente.iniciaTelaParaGerente(usuario, filial);
		}
	}
	
	protected void inicializaConfiguracoesIniciais(){
		FilialSupermercado filial = Sistema.getListaFilialSupermercado().get(0);
		Gerente gerente = filial.getGerente();
		List<Funcionario> listaFuncionarios = filial.getListaFuncionarios();
		List<Caixa> listaCaixaMercado = Sistema.getListaCaixaMercado();

		gerente.alocaFuncionarioNoCaixa(listaCaixaMercado.get(0), listaFuncionarios.get(0));
		gerente.alocaFuncionarioNoCaixa(listaCaixaMercado.get(1), listaFuncionarios.get(1));
		gerente.alocaFuncionarioNoCaixa(listaCaixaMercado.get(2), listaFuncionarios.get(2));
	}

	/**
	 * @param teclado
	 * @return
	 */
	private Usuario inicializaTelaLoginFuncionario() {
		Usuario usuario = null;
		do {
			@SuppressWarnings("resource")
			Scanner teclado = new Scanner(System.in);
			String login, senha;
			
			System.out.println("Bem vindo ao " + Constantes.SUPERMERCADO_VEM_QUEM_TEM);
			System.out.println("Digite seu Login: ");
			login = teclado.nextLine();
			System.out.println("Digite sua Senha: ");
			senha = teclado.nextLine();
			
			usuario = Usuario.realizaLogin(login, senha);
			
		} while (usuario == null);
		return usuario;
	}	
	
	/**
	 * Printar linhas em branco 
	 */
	public void printaLinhaEmBranco() {
		System.out.println("");
	}
	
	/**
	 * @param resposta
	 * @return
	 */
	private boolean validaRespostaTelaInicial(int resposta) {
		return resposta == 1 || resposta == 2;
	}
	
}
