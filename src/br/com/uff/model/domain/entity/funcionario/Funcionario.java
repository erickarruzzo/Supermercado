package br.com.uff.model.domain.entity.funcionario;

import java.util.Date;

import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.operations.OperacoesGerais;
import br.com.uff.model.domain.valueobject.TipoVenda;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.persitence.Sistema;

/**
 * Classe Funcionario
 * @author lucas.fernandes
 *
 */
public class Funcionario extends FuncionarioAbstract implements OperacoesGerais{

	/*
	 * Construtor Funcionário
	 */
	public Funcionario(String login, String senha, String id, String nome, Date dataAniversario,
			Date dataContratacao, double salario) {
		super(login, senha, id, nome, dataAniversario, dataContratacao, salario);
	}
	
	public Funcionario() {
		super();
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public double visualizaPrecoProdutoByNome(String nomeProduto, TipoVenda tipoVenda) {
		Produto produto = Sistema.getProdutoByNome(nomeProduto);
		if(tipoVenda instanceof Unidade){
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
	
}
