package br.com.uff.model.domain.exceptions;

/**
 * Excecão relacionado a classe Produto
 * @author lucas.fernandes
 *
 */
public class ProdutoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoException(String msg){
		super(msg);
	}
	
}
