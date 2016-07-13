package br.com.uff.model.domain.entity.cliente;

import java.util.Date;

import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.operations.OperacoesGerais;
import br.com.uff.model.domain.valueobject.Compra;
import br.com.uff.model.domain.valueobject.ItemVenda;
import br.com.uff.model.domain.valueobject.TipoVenda;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.persitence.Sistema;

public class Cliente implements OperacoesGerais{

	private String nome;
	private Date dataNascimento;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	
	public ItemVenda selecionaProdutoParaCompra(String idProduto, Compra compra){
			Produto produto = Produto.getProdutoById(idProduto);
			return new ItemVenda(produto, compra);
	}
	
}
