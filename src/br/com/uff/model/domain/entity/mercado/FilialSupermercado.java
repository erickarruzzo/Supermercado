package br.com.uff.model.domain.entity.mercado;

import java.util.List;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.valueobject.enums.FilialUnidade;

public class FilialSupermercado extends Supermercado{

	private FilialUnidade filialUnidade;
	private String endereco;
	private Estoque estoque;
	private Gerente gerente;
	private List<Funcionario> listaFuncionarios;
	private List<Caixa> listaCaixas;
	
	public FilialSupermercado(FilialUnidade filialUnidade, String endereco,
			Estoque estoque, Gerente gerente,
			List<Funcionario> listaFuncionarios, List<Caixa> listaCaixas) {
		super();
		this.filialUnidade = filialUnidade;
		this.endereco = endereco;
		this.estoque = estoque;
		this.gerente = gerente;
		this.listaFuncionarios = listaFuncionarios;
		this.listaCaixas = listaCaixas;
	}

	public FilialUnidade getFilialUnidade() {
		return filialUnidade;
	}

	public void setFilialUnidade(FilialUnidade filialUnidade) {
		this.filialUnidade = filialUnidade;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Estoque getEstoque() {
		return estoque;
	}
	
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public Gerente getGerente() {
		return gerente;
	}
	
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Caixa> getListaCaixas() {
		return listaCaixas;
	}

	public void setListaCaixas(List<Caixa> listaCaixas) {
		this.listaCaixas = listaCaixas;
	}
	
}
