package br.com.uff.model.domain.exceptions;

public class PagamentoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PagamentoException(String msg){
		super(msg);
	}
	
}
