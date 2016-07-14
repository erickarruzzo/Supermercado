package br.com.uff.model.domain.entity.usuario;

import java.util.List;
import java.util.Map;

import br.com.uff.model.domain.entity.funcionario.FuncionarioAbstract;
import br.com.uff.model.domain.exceptions.UsuarioException;
import br.com.uff.persitence.Sistema;


public class Usuario {

	private String login;
	private String senha;
	
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario() {}
	
	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return Usuário logado, ou nulo
	 */
	public static Usuario realizaLogin(String login, String senha){
		try {
			return verificaUsuarioValido(login, senha);
		} catch (UsuarioException exception) {
			System.out.println(exception.getMessage());
			return null;
		}
	}

	/**
	 * Verifica o login e senha informados batem com algum usuário
	 * @param login
	 * @param senha
	 * @return TRUE se o usuário é válido, FALSE caso contrário
	 * @throws UsuarioException 
	 */
	private static Usuario verificaUsuarioValido(String login, String senha) throws UsuarioException {
		List<Usuario> listaUsuario = Sistema.getListaUsuarios();
		for (Usuario usuarioCadastrado : listaUsuario) {
			if(usuarioCadastrado.getLogin().equals(login)){
				Map<FuncionarioAbstract, String> mapaFuncionarioSenha = Sistema.getMapaFuncionarioSenha();
				if(mapaFuncionarioSenha.get(usuarioCadastrado).equals(senha)){
					return usuarioCadastrado;
				} else{
					throw new UsuarioException("Senha inválida. Tente Novamente ou cadastra-se no Sistema");
				}
			}
		}
		throw new UsuarioException("Login inválido. Tente Novamente ou cadastra-se no Sistema");

	}
	
}
