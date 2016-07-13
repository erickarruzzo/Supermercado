package br.com.uff.model.domain.exceptions;

public class LoginException extends RuntimeException{
	
	private static final long serialVersionUID = 1276727019536603615L;

	public LoginException(String msg) {
		super(msg);
	}

}
