package br.com.uff.view;

import br.com.uff.controller.ControladorGeral;

/**
 * Programa Principal que simula as a��es do sistema
 *
 */
public class MainProgram {

	public static void main(String[] args) {
		ControladorGeral controllerGeral = new ControladorGeral();
		controllerGeral.inicializaTelaPrincipal();
	}

}
