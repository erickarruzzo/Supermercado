package br.com.uff.model.domain.entity.funcionario;

import java.util.Date;

import br.com.uff.model.domain.entity.usuario.Usuario;

/**
 * Classe Abstrata para FuncionÃ¡rios do Sistema de Supermecado
 * @author lucas.fernandes
 *
 */
public abstract class FuncionarioAbstract extends Usuario{

	protected String id;
	protected String nome;
	protected Date dataAniversario;
	protected Date dataContratacao;
	protected double salario;
	
	protected FuncionarioAbstract() {}
	
	/*
	 * Construtor Básico
	 */
	protected FuncionarioAbstract(String login, String senha, String id, String nome, Date dataAniversario,
			Date dataContratacao, double salario) {
		super(login, senha);
		this.id = id;
		this.nome = nome;
		this.dataAniversario = dataAniversario;
		this.dataContratacao = dataContratacao;
		this.salario = salario;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataAniversario() {
		return dataAniversario;
	}
	
	protected void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	public Date getDataContratacao() {
		return dataContratacao;
	}
	
	protected void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	public double getSalario() {
		return salario;
	}
	
	protected void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataAniversario == null) ? 0 : dataAniversario.hashCode());
		result = prime * result
				+ ((dataContratacao == null) ? 0 : dataContratacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioAbstract other = (FuncionarioAbstract) obj;
		if (dataAniversario == null) {
			if (other.dataAniversario != null)
				return false;
		} else if (!dataAniversario.equals(other.dataAniversario))
			return false;
		if (dataContratacao == null) {
			if (other.dataContratacao != null)
				return false;
		} else if (!dataContratacao.equals(other.dataContratacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(salario) != Double
				.doubleToLongBits(other.salario))
			return false;
		return true;
	}
	
}
