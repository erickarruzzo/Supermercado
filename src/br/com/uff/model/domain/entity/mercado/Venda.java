package br.com.uff.model.domain.entity.mercado;

import java.util.List;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.ItemVenda;
import br.com.uff.model.domain.valueobject.Pagamento;

public class Venda {

	private Funcionario funcionarioResponsavel;
	private double valorVendaFinal;
	private List<ItemVenda> listaVendasUnitarias;
	private Pagamento pagamento;
	private Estoque estoque;
	
	public Venda(Funcionario funcionarioResponsavel, List<ItemVenda> listaVendasUnitarias, 
			Pagamento pagamento, Estoque estoque) {
		super();
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.listaVendasUnitarias = listaVendasUnitarias;
		this.pagamento = pagamento;
		this.valorVendaFinal = calculaValorVendaFinal(listaVendasUnitarias, estoque);
		this.estoque = estoque;
	}
	
	private double calculaValorVendaFinal(List<ItemVenda> listaVendasUnitarias, Estoque estoque){
		try {
			double valor = 0;
			for (ItemVenda itemVenda : listaVendasUnitarias) {
				valor += itemVenda.calculaValorVenda(estoque);
			}
			return valor;
		} catch (ProdutoException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public List<ItemVenda> getListaVendasUnitarias() {
		return listaVendasUnitarias;
	}
	
	public void setListaVendasUnitarias(List<ItemVenda> listaVendasUnitarias) {
		this.listaVendasUnitarias = listaVendasUnitarias;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public double getValorVendaFinal() {
		return valorVendaFinal;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listaVendasUnitarias == null) ? 0 : listaVendasUnitarias.hashCode());
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
		Venda other = (Venda) obj;
		if (listaVendasUnitarias == null) {
			if (other.listaVendasUnitarias != null)
				return false;
		} else if (!listaVendasUnitarias.equals(other.listaVendasUnitarias))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionário Responsável pela Venda: " + funcionarioResponsavel + 
				", Valor da venda: " + valorVendaFinal + ", " +
				pagamento;
	}
	
	
}
