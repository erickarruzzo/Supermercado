package br.com.uff.model.domain.entity.mercado;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.exceptions.PagamentoException;
import br.com.uff.model.domain.operations.OperacoesGerais;
import br.com.uff.model.domain.operations.OperacoesVenda;
import br.com.uff.model.domain.valueobject.RelatorioVenda;
import br.com.uff.model.domain.valueobject.TipoVenda;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.persitence.Sistema;

public class Caixa implements OperacoesVenda, OperacoesGerais{

	private String id;
	private Funcionario funcionarioResponsavel;
	private static int quantidadeCaixa = 3;
	private List<Venda> listaVendasRealizadasCaixa = new LinkedList<Venda>();
	
	public Caixa(Funcionario funcionarioResponsavel, String id) {
		super();
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public static int getQuantidadeCaixa() {
		return quantidadeCaixa;
	}

	public List<Venda> getListaVendasRealizadasCaixa() {
		return listaVendasRealizadasCaixa;
	}

	public void setListaVendasRealizadasCaixa(List<Venda> listaVendasRealizadasCaixa) {
		this.listaVendasRealizadasCaixa = listaVendasRealizadasCaixa;
	}
	
	@Override
	public void finalizaVenda(Venda venda) throws RuntimeException {
		if(venda.getValorVendaFinal()>0){
			if(venda.getPagamento().getValor() < venda.getValorVendaFinal()){
				throw new PagamentoException(Constantes.PAGAMENTO_ERRO);
			}else if(venda.getPagamento().getValor() > venda.getValorVendaFinal()){
				venda.getPagamento().setTroco(venda.getPagamento().getValor() - venda.getValorVendaFinal());
			}
			venda.getEstoque().baixaVendaEstoque(venda);
			listaVendasRealizadasCaixa.add(venda);
		}
	}

	@Override
	public double visualizaPrecoProdutoByNome(String nomeProduto) {
		Produto produto = Sistema.getProdutoByNome(nomeProduto);
		if(produto.getPrecoUnitario() != 0){
			return produto.getPrecoUnitario();
		} else{
			return produto.getPrecoQuilo();
		}
	}
	
	@Override
	public double visualizaPrecoProdutoById(String idProduto, TipoVenda tipoVenda) {
		Produto produto = Produto.getProdutoById(idProduto);
		if(tipoVenda instanceof Unidade){
			return produto.getPrecoUnitario();
		} else{
			return produto.getPrecoQuilo();
		}
	}
	
	public RelatorioVenda geraRelatorioCaixa(){
		RelatorioVenda relatorio = new RelatorioVenda(funcionarioResponsavel, this,
				new Date(), calculaVendaTotais(), listaVendasRealizadasCaixa);
		return relatorio;
	}
	
	private double calculaVendaTotais(){
		double valorTotal = 0;
		for (Venda venda : listaVendasRealizadasCaixa) {
			valorTotal += venda.getValorVendaFinal();
		}
		return valorTotal;
	}
	
}
