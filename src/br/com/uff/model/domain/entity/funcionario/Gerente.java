package br.com.uff.model.domain.entity.funcionario;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.ItemEstoque;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.operations.OperacoesEstoque;
import br.com.uff.model.domain.operations.OperacoesGerais;
import br.com.uff.model.domain.operations.OperacoesGerenciais;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.RelatorioEstoque;
import br.com.uff.model.domain.valueobject.RelatorioVenda;
import br.com.uff.model.domain.valueobject.TipoVenda;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.persitence.Sistema;

public class Gerente extends FuncionarioAbstract implements OperacoesGerenciais, OperacoesEstoque, OperacoesGerais{
	
	public Gerente() {
		super();
	}
	
	public Gerente(String login, String senha, String id, String nome, Date dataAniversario,
			Date dataContratacao, double salario) {
		super(login, senha, id, nome, dataAniversario, dataContratacao, salario);
	}

	/**
	 * Aloca funcionário no Caixa
	 * @param funcionario
	 * @return
	 */
	public Caixa alocaFuncionarioNoCaixa(Caixa caixa, Funcionario funcionario){
		for (Caixa caixaMercado: Sistema.getListaCaixaMercado()) {
			if (caixaMercado.getId().equals(caixa.getId())) {
				caixa.setFuncionarioResponsavel(funcionario);
				caixaMercado = caixa;
			}
		}
		return caixa;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public void adicionaProdutoNoEstoque(Produto produto, String idEstoque, Unidade unidade, Quilo quilo) {
		Estoque estoque = Estoque.getEstoqueById(idEstoque);
		ItemEstoque itemEstoque = new ItemEstoque(produto, unidade, quilo);
		List<ItemEstoque> listaItemEstoque = new LinkedList<ItemEstoque>();
		listaItemEstoque = estoque.getListaItensEstoque();
		listaItemEstoque.add(itemEstoque);
		estoque.setListaItensEstoque(listaItemEstoque);
		
		adicionaProdutoListaProdutosMercado(produto);
	}

	/**
	 * Além de adicionar no estoque, o novo produto deve ser adicionado na lista de produtos do Mercado
	 * afim de guardar histórico de todos os produtos mesmo que não estejam mais em estoque
	 * @param produto
	 */
	private void adicionaProdutoListaProdutosMercado(Produto produto) {
		List<Produto> listaProduto = new LinkedList<Produto>(Sistema.getListaProduto());
		listaProduto.add(produto);
		Sistema.setListaProduto(listaProduto);
		
	}

	@Override
	public void baixaEstoque(Produto produto, String idEstoque) {
		Estoque estoque = Estoque.getEstoqueById(idEstoque);
		List<ItemEstoque> listaItemEstoque = new LinkedList<ItemEstoque>();
		listaItemEstoque = estoque.getListaItensEstoque();
		for (ItemEstoque itemEstoque : listaItemEstoque) {
			if(itemEstoque.getProduto().equals(produto)){
				listaItemEstoque.remove(itemEstoque);
				break;
			}
		}
		estoque.setListaItensEstoque(listaItemEstoque);
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
	public double visualizaPrecoProdutoById(String idProduto,TipoVenda tipoVenda) {
		Produto produto = Produto.getProdutoById(idProduto);
		if(tipoVenda instanceof Unidade){
			return produto.getPrecoUnitario();
		} else{
			return produto.getPrecoQuilo();
		}
	}

	@Override
	public RelatorioVenda geraRelatorioVendas(String idCaixa) {
		Caixa caixa = Sistema.getCaixaById(idCaixa);
		return caixa.geraRelatorioCaixa();
	}

	@Override
	public List<RelatorioVenda> geraListaRelatorioVendas() {
		List<Caixa> listaCaixaMercado = Sistema.getListaCaixaMercado();
		List<RelatorioVenda> listaRelatorios = new LinkedList<RelatorioVenda>();
		for (Caixa caixa : listaCaixaMercado) {
			listaRelatorios.add(geraRelatorioVendas(caixa.getId())); 
		}
		return listaRelatorios;
	}

	@Override
	public RelatorioEstoque geraRelatorioEstoque(String idEstoque) {
		Estoque estoque = Estoque.getEstoqueById(idEstoque);
		return estoque.gerarRelatorioEstoque(this);
	}	
	
}
