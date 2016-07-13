package br.com.uff.model.domain.valueobject;

import br.com.uff.model.domain.valueobject.enums.FormaPagamento;

public class Pagamento {

	private double valor;
	private double troco;
	private FormaPagamento formaPagamento;
	
	public Pagamento(double valor, FormaPagamento formaPagamento) {
		super();
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public String toString() {
		return imprimePagamento();
	}
	
	private String imprimePagamento(){
		StringBuffer stringBuffer = new StringBuffer("Pagamento: " + valor);
		if(troco != 0){
			stringBuffer.append(", Troco: " + troco);
		}
		stringBuffer.append(", Forma de Pagamento: " + formaPagamento.name());
		return stringBuffer.toString();
	}
	
	
}
