package br.com.uff.model.domain.exceptions;

public class FuncionarioException extends RuntimeException {

	private static final long serialVersionUID = 6433956139916189956L;

	public FuncionarioException(String msgErro) {
		super(msgErro);
	}
	
}
