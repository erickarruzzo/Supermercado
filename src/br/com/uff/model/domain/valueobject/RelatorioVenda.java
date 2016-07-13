package br.com.uff.model.domain.valueobject;

import java.util.Date;
import java.util.List;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.Venda;

public class RelatorioVenda extends Relatorio{

	private Caixa caixa;
	private double vendasTotais;
	private List<Venda> listaVendas;
	
	public RelatorioVenda(Funcionario funcionario, Caixa caixa,
			Date dataRelatorio, double vendasTotais, List<Venda> listaVendas) {
		super();
		this.funcionario = funcionario;
		this.caixa = caixa;
		this.dataRelatorio = dataRelatorio;
		this.vendasTotais = vendasTotais;
		this.listaVendas = listaVendas;
	}

	public Caixa getCaixa() {
		return caixa;
	}
	
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
	
	public double getVendasTotais() {
		return vendasTotais;
	}
	
	public void setVendasTotais(double vendasTotais) {
		this.vendasTotais = vendasTotais;
	}
	
	public List<Venda> getListaVendas() {
		return listaVendas;
	}
	
	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	@Override
	public String toString() {
		return imprimeRelatorioVenda();
	}
	
	private String imprimeRelatorioVenda(){
		StringBuffer stringBuffer = new StringBuffer();
		if(!listaVendas.isEmpty()){
			for (Venda venda : listaVendas) {
				stringBuffer.append("Nº Caixa: " + caixa.getId() + ", ");
				stringBuffer.append("Venda total: R$" + vendasTotais + ", ");
				stringBuffer.append(venda.toString());
			}
			return stringBuffer.toString();
		} else{
			return "Não foi efetuada nenhuma venda nesse caixa";
		}
		
	}
	
}
