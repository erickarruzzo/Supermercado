package br.com.uff.test.gerente;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.mercado.ItemEstoque;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.model.domain.valueobject.enums.TipoProduto;
import br.com.uff.persitence.Sistema;

public class GerenteOperationsTest {

	private List<FilialSupermercado> listaFilialSupermercados = Sistema.getListaFilialSupermercado();
	private FilialSupermercado filial = listaFilialSupermercados.get(0);
	private Gerente gerente = filial.getGerente();
	private List<Funcionario> listaFuncionarios = filial.getListaFuncionarios();
	private List<Caixa> listaCaixaMercado = Sistema.getListaCaixaMercado();
	private Caixa caixa1 = listaCaixaMercado.get(0);
	private	Caixa caixa2 = listaCaixaMercado.get(1);
	private Caixa caixa3 = listaCaixaMercado.get(2);
	
	/**
	 * Teste para alocacão de funcionário José Almeida no caixa 1
	 */
	@Test
	public void alocaFuncionarioNoCaixa1Test(){
		Funcionario funcionario = listaFuncionarios.get(0);
		String nomeFuncionario = funcionario.getNome();
		caixa1 = gerente.alocaFuncionarioNoCaixa(caixa1, funcionario);
		assertEquals(nomeFuncionario, caixa1.getFuncionarioResponsavel().getNome());
	}
	
	/**
	 * Teste para alocacão de funcionário Mariana Rocha no caixa 2
	 */
	@Test
	public void alocaFuncionarioNoCaixa2Test(){
		Funcionario funcionario = listaFuncionarios.get(1);
		String nomeFuncionario = funcionario.getNome();
		caixa2 = gerente.alocaFuncionarioNoCaixa(caixa2, funcionario);
		assertEquals(nomeFuncionario, caixa2.getFuncionarioResponsavel().getNome());
	}
	
	/**
	 * Teste para alocacão de funcionário Mariana da Silva no caixa 3
	 */
	@Test
	public void alocaFuncionarioNoCaixa3Test(){
		Funcionario funcionario = listaFuncionarios.get(2);
		String nomeFuncionario = funcionario.getNome();
		caixa3 = gerente.alocaFuncionarioNoCaixa(caixa3, funcionario);
		assertEquals(nomeFuncionario, caixa3.getFuncionarioResponsavel().getNome());
	}
	
	/**
	 * Teste para adição de novo produto em estoque
	 * Adiciona um novo produto e verifico se o tamanho da lista de produtos em estoque aumentou
	 */
	@Test
	public void adicionaProdutoNovoEstoqueTest(){
		Produto produtoNovo = new Produto("30", "Sorvete", "Kibom", TipoProduto.ALIMENTO, 3.99, 0.0);
		Estoque estoqueFilial1 = filial.getEstoque();
		int quantidadeItensEstoqueEstoque = estoqueFilial1.getListaItensEstoque().size();
		gerente.adicionaProdutoNoEstoque(produtoNovo, estoqueFilial1.getId(), new Unidade(30), null);
		assertEquals(quantidadeItensEstoqueEstoque + 1, estoqueFilial1.getListaItensEstoque().size());
	}

	/**
	 * Teste para baixa de um produto em estoque
	 * Remove um produto e verifico se o tamanho da lista de produtos em estoque diminuiu
	 */
	@Test
	public void baixaProdutoEstoqueTest(){
		Estoque estoque = filial.getEstoque();
		List<ItemEstoque> listaItensEstoque = estoque.getListaItensEstoque();
		Produto produto = listaItensEstoque.get(0).getProduto();
		int quantidadeItensEstoque = estoque.getListaItensEstoque().size();
		gerente.baixaEstoque(produto, estoque.getId());
		assertEquals(quantidadeItensEstoque - 1, estoque.getListaItensEstoque().size());
	}

	/**
	 * Teste para visualizar preço do produto
	 */
	@Test
	public void visualizaPrecoProduto(){
		Estoque estoque = filial.getEstoque();
		List<ItemEstoque> listaItensEstoque = estoque.getListaItensEstoque();
		Produto produto = listaItensEstoque.get(0).getProduto();
		String idProduto = produto.getId();
		double preco = produto.getPrecoUnitario();
		
		assertEquals(preco, gerente.visualizaPrecoProdutoById(idProduto, new Unidade(0)), 0.001);
		preco = produto.getPrecoQuilo();
		assertEquals(preco, gerente.visualizaPrecoProdutoById(idProduto, new Quilo(0)), 0.001);
		
	}
	
}
