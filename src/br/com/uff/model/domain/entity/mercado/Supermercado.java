package br.com.uff.model.domain.entity.mercado;

import br.com.uff.model.domain.constants.Constantes;


public abstract class Supermercado {

	protected String nome = Constantes.SUPERMERCADO_VEM_QUEM_TEM;

	protected String getNome() {
		return nome;
	}
	
}
