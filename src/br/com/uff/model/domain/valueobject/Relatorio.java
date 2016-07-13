package br.com.uff.model.domain.valueobject;

import java.util.Date;

import br.com.uff.model.domain.entity.funcionario.FuncionarioAbstract;

public class Relatorio {

	protected FuncionarioAbstract funcionario;
	protected Date dataRelatorio;

	public FuncionarioAbstract getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioAbstract funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataRelatorio() {
		return dataRelatorio;
	}

	public void setDataRelatorio(Date dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}

}
