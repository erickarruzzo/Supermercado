package br.com.uff.persitence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.uff.model.domain.constants.Constantes;
import br.com.uff.model.domain.entity.funcionario.Funcionario;
import br.com.uff.model.domain.entity.funcionario.FuncionarioAbstract;
import br.com.uff.model.domain.entity.funcionario.Gerente;
import br.com.uff.model.domain.entity.mercado.Caixa;
import br.com.uff.model.domain.entity.mercado.Estoque;
import br.com.uff.model.domain.entity.mercado.FilialSupermercado;
import br.com.uff.model.domain.entity.mercado.ItemEstoque;
import br.com.uff.model.domain.entity.mercado.Produto;
import br.com.uff.model.domain.entity.usuario.Usuario;
import br.com.uff.model.domain.exceptions.CaixaException;
import br.com.uff.model.domain.exceptions.FuncionarioException;
import br.com.uff.model.domain.exceptions.ProdutoException;
import br.com.uff.model.domain.valueobject.Quilo;
import br.com.uff.model.domain.valueobject.Unidade;
import br.com.uff.model.domain.valueobject.enums.FilialUnidade;
import br.com.uff.model.domain.valueobject.enums.TipoProduto;

/**
 * Classe para inicializar o sistema. Será substituída pela Persistencia
 *
 */
public class Sistema {

	private static List<Gerente> listaGerente = inicializaGerente();
	private static List<Funcionario> listaFuncionarios = inicializaFuncionarios();
	private static List<Produto> listaProduto = inicializaProdutos(); 
	private static List<ItemEstoque> listaItemEstoque = inicializaItemEstoque();
	private static List<Estoque> listaEstoque = inicializaEstoques();
	private static List<Caixa> listaCaixaMercado = inicializaListaCaixa();
	private static List<FilialSupermercado> listaFilialSupermercado = inicializaFiliasSupermercado();
	private static List<Usuario> listaUsuarios = inicializaListaUsuarios();
	private static Map<FuncionarioAbstract, String> mapaUsuarioSenha = inicializaMapFuncionarioSenha();
	private static List<TipoProduto> listaTiposProdutosMercado = inicializaListaTiposProdutos();
	
	public static List<Gerente> getListaGerente() {
		return listaGerente;
	}
	
	public static void setListaGerente(List<Gerente> listaGerente) {
		Sistema.listaGerente = listaGerente;
	}
	
	public static List<FilialSupermercado> getListaFilialSupermercado() {
		return listaFilialSupermercado;
	}
	

	public static void setListaFilialSupermercado(
			List<FilialSupermercado> listaFilialSupermercado) {
		Sistema.listaFilialSupermercado = listaFilialSupermercado;
	}

	public static List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public static void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		Sistema.listaFuncionarios = listaFuncionarios;
	}

	public static List<Produto> getListaProduto() {
		return listaProduto;
	}

	public static void setListaProduto(List<Produto> listaProduto) {
		Sistema.listaProduto = listaProduto;
	}

	public static List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public static void setListaEstoque(List<Estoque> listaEstoque) {
		Sistema.listaEstoque = listaEstoque;
	}

	public static List<ItemEstoque> getListaItemEstoque() {
		return listaItemEstoque;
	}

	public static void setListaItemEstoque(List<ItemEstoque> listaItemEstoque) {
		Sistema.listaItemEstoque = listaItemEstoque;
	}

	public static List<Caixa> getListaCaixaMercado() {
		return listaCaixaMercado;
	}

	public static void setListaCaixaMercado(List<Caixa> listaCaixaMercado) {
		Sistema.listaCaixaMercado = listaCaixaMercado;
	}

	public static Map<FuncionarioAbstract, String> getMapaFuncionarioSenha() {
		return mapaUsuarioSenha;
	}

	public static void setMapaFuncionarioSenha(Map<FuncionarioAbstract, String> mapaFuncionarioSenha) {
		Sistema.mapaUsuarioSenha = mapaFuncionarioSenha;
	}

	public static List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public static void setListaUsuarios(List<Usuario> listaUsuarios) {
		Sistema.listaUsuarios = listaUsuarios;
	}
	
	public static List<TipoProduto> getListaTiposProdutosMercado() {
		return listaTiposProdutosMercado;
	}

	public static void setListaTiposProdutosMercado(
			List<TipoProduto> listaTiposProdutosMercado) {
		Sistema.listaTiposProdutosMercado = listaTiposProdutosMercado;
	}

	private static List<Gerente> inicializaGerente() {
		List<Gerente> listaGerentes = new LinkedList<Gerente>();
		
		Calendar calendarDataAniversario = Calendar.getInstance();
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 2);
		calendarDataAniversario.set(Calendar.MONTH, 7);
		calendarDataAniversario.set(Calendar.YEAR, 1993);
		
		Calendar calendarDataContratacao = Calendar.getInstance();
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		Date dataAniversario = new Date();
		dataAniversario = calendarDataAniversario.getTime();
		Date dataContratacao = new Date();
		dataContratacao = calendarDataContratacao.getTime();
		
		Gerente gerente = new Gerente("Lucas Fernandes", "654321","01", "Lucas Fernandes", dataAniversario, dataContratacao, 7500.00);
		
		listaGerentes.add(gerente);
		
		return listaGerentes;
	}
	
	private static List<Funcionario> inicializaFuncionarios(){
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		
		Calendar calendarDataAniversario = Calendar.getInstance();
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 17);
		calendarDataAniversario.set(Calendar.MONTH, 11);
		calendarDataAniversario.set(Calendar.YEAR, 1984);
		
		Calendar calendarDataContratacao = Calendar.getInstance();
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		Date dataAniversario = new Date();
		dataAniversario = calendarDataAniversario.getTime();
		Date dataContratacao = new Date();
		dataContratacao = calendarDataContratacao.getTime();
		
		Funcionario funcionario = new Funcionario("Sherlock Homes", "123456", "001", "Sherlock Homes", dataAniversario, dataContratacao, 2300.50);
		
		listaFuncionarios.add(funcionario);
		
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 5);
		calendarDataAniversario.set(Calendar.MONTH, 3);
		calendarDataAniversario.set(Calendar.YEAR, 1989);
		
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		dataAniversario = calendarDataAniversario.getTime();
		dataContratacao = calendarDataContratacao.getTime();
		
		funcionario = new Funcionario("Mariana Rocha", "123456", "002", "Mariana Rocha", dataAniversario, dataContratacao, 6000.00);
		
		listaFuncionarios.add(funcionario);
		
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 13);
		calendarDataAniversario.set(Calendar.MONTH, 9);
		calendarDataAniversario.set(Calendar.YEAR, 1997);
		
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		dataAniversario = calendarDataAniversario.getTime();
		dataContratacao = calendarDataContratacao.getTime();
		
		funcionario = new Funcionario("Maria da Silva", "123456", "003", "Maria da Silva", dataAniversario, dataContratacao, 5000.00);
		
		listaFuncionarios.add(funcionario);
		
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 25);
		calendarDataAniversario.set(Calendar.MONTH, 5);
		calendarDataAniversario.set(Calendar.YEAR, 1990);
		
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		dataAniversario = calendarDataAniversario.getTime();
		dataContratacao = calendarDataContratacao.getTime();
		
		funcionario = new Funcionario("Andrea Dutra", "123456", "004", "Andrea Dutra", dataAniversario, dataContratacao, 1800.00);
		
		listaFuncionarios.add(funcionario);
		
		calendarDataAniversario.set(Calendar.DAY_OF_MONTH, 12);
		calendarDataAniversario.set(Calendar.MONTH, 19);
		calendarDataAniversario.set(Calendar.YEAR, 1980);
		
		calendarDataContratacao.set(Calendar.DAY_OF_MONTH, 11);
		calendarDataContratacao.set(Calendar.MONTH, 3);
		calendarDataContratacao.set(Calendar.YEAR, 2009);
		
		dataAniversario = calendarDataAniversario.getTime();
		dataContratacao = calendarDataContratacao.getTime();
		
		funcionario = new Funcionario("Fernando Fernandes", "123456", "004", "Fernando Fernandes", dataAniversario, dataContratacao, 5700.00);
		
		listaFuncionarios.add(funcionario);
		
		return listaFuncionarios;
	}
	
	private static List<Produto> inicializaProdutos(){
		List<Produto> listaProdutos = new ArrayList<Produto>();
		Produto produto = new Produto("1", "Leite", "Ninho", TipoProduto.BEBIDA, 2.99, 0);
		listaProdutos.add(produto);
		
		produto = new Produto("2", "Alcatra", "Friboi", TipoProduto.CARNE, 0, 12.0);
		listaProdutos.add(produto);
		
		produto = new Produto("3", "Biscoito", "Passatempo", TipoProduto.ALIMENTO, 1.99, 0);
		listaProdutos.add(produto);
		
		produto = new Produto("4", "Feijão", "Maximo", TipoProduto.ALIMENTO, 0, 4.95);
		listaProdutos.add(produto);
		
		produto = new Produto("5", "Arroz", "Tio João", TipoProduto.ALIMENTO, 2.99, 0);
		listaProdutos.add(produto);
		
		produto = new Produto("6", "Acucar", "União", TipoProduto.ALIMENTO, 0, 3.20);
		listaProdutos.add(produto);
		
		produto = new Produto("7", "Manteiga", "Qualy", TipoProduto.ALIMENTO, 4.00, 0);
		listaProdutos.add(produto);
		
		return listaProdutos;
	}
	
	public static List<ItemEstoque> inicializaItemEstoque(){
		listaItemEstoque = new ArrayList<ItemEstoque>();
		Unidade unidade = new Unidade(250);
		ItemEstoque itemEstoque = new ItemEstoque(listaProduto.get(0), unidade, null);
		listaItemEstoque.add(itemEstoque);
		
		Quilo quilo = new Quilo(350);
		itemEstoque = new ItemEstoque(listaProduto.get(1), null, quilo);
		listaItemEstoque.add(itemEstoque);
		
		unidade = new Unidade(100);
		itemEstoque = new ItemEstoque(listaProduto.get(2), unidade, null);
		listaItemEstoque.add(itemEstoque);
		
		quilo = new Quilo(150);
		itemEstoque = new ItemEstoque(listaProduto.get(3), null, quilo);
		listaItemEstoque.add(itemEstoque);
		
		unidade = new Unidade(200);
		itemEstoque = new ItemEstoque(listaProduto.get(4), unidade, null);
		listaItemEstoque.add(itemEstoque);
		
		quilo = new Quilo(175);
		itemEstoque = new ItemEstoque(listaProduto.get(5), null, quilo);
		listaItemEstoque.add(itemEstoque);
		
		unidade = new Unidade(300);
		itemEstoque = new ItemEstoque(listaProduto.get(6), unidade, null);
		listaItemEstoque.add(itemEstoque);
		
		return listaItemEstoque;
	}
	
	private static List<Estoque> inicializaEstoques(){
		listaEstoque = new ArrayList<Estoque>();
		Estoque estoque1 = new Estoque("1", Constantes.ENDERECO_MEIER, 1000, listaItemEstoque);
		listaEstoque.add(estoque1);
		return listaEstoque;
	}
	
	private static List<FilialSupermercado> inicializaFiliasSupermercado(){
		listaFilialSupermercado = new ArrayList<FilialSupermercado>();
		FilialSupermercado filialSupermercado = new FilialSupermercado(FilialUnidade.MEIER, Constantes.ENDERECO_MEIER, 
				listaEstoque.get(0), listaGerente.get(0), listaFuncionarios, listaCaixaMercado);
		listaFilialSupermercado.add(filialSupermercado);
		return listaFilialSupermercado;
		
	}
	
	private static List<Caixa> inicializaListaCaixa(){
		listaCaixaMercado = new ArrayList<Caixa>(3);
		Caixa caixa = new Caixa(null, "1");
		listaCaixaMercado.add(caixa);
		caixa = new Caixa(null, "2");
		listaCaixaMercado.add(caixa);
		caixa = new Caixa(null, "3");
		listaCaixaMercado.add(caixa);
		return listaCaixaMercado;
	}
	
	
	private static Map<FuncionarioAbstract, String> inicializaMapFuncionarioSenha(){
		mapaUsuarioSenha = new HashMap<FuncionarioAbstract, String>();
		mapaUsuarioSenha.put(listaFuncionarios.get(0), listaFuncionarios.get(0).getSenha());
		mapaUsuarioSenha.put(listaFuncionarios.get(1), listaFuncionarios.get(1).getSenha());
		mapaUsuarioSenha.put(listaFuncionarios.get(2), listaFuncionarios.get(2).getSenha());
		mapaUsuarioSenha.put(listaFuncionarios.get(3), listaFuncionarios.get(3).getSenha());
		mapaUsuarioSenha.put(listaFuncionarios.get(4), listaFuncionarios.get(4).getSenha());
		mapaUsuarioSenha.put(listaGerente.get(0), listaGerente.get(0).getSenha());
		return mapaUsuarioSenha;
	}
	
	private static List<Usuario> inicializaListaUsuarios() {
		List<Usuario> listaUsuarios = new LinkedList<Usuario>();
		for (Funcionario funcionario : listaFuncionarios) {
			listaUsuarios.add(funcionario);
		}
		listaUsuarios.add(listaGerente.get(0));
		return listaUsuarios;
	}
	
	public static Gerente getGerenteByLogin(String login){
		for (Gerente gerente: listaGerente) {
			if(login.equals(gerente.getLogin())){
				return gerente;
			}
		}
		return null;
	}
	
	public static Funcionario getFuncionarioByLogin(String login){
		for (Funcionario funcionario: listaFuncionarios) {
			if(login.equals(funcionario.getLogin())){
				return funcionario;
			}
		}
		return null;
	}
	
	public static Funcionario getFuncionarioByName(String nome){
		for (Funcionario funcionario : listaFuncionarios) {
			if(nome.equals(funcionario.getNome())){
				return funcionario;
			}
		}
		throw new FuncionarioException("Não existe Funcionário cadastrado com esse nome!");
	}
	
	public static Caixa getCaixaById(String id){
		List<Caixa> listaCaixa = Sistema.getListaCaixaMercado();
		for (Caixa caixa : listaCaixa) {
			if(caixa.getId().equals(id)){
				return caixa;
			}
		}
		throw new CaixaException(Constantes.CAIXA_NAO_EXISTE);
	}
	
	public static Produto getProdutoByNome(String nomeProduto){
		for (Produto produto : Sistema.getListaProduto()) {
			if(produto.getNome().equals(nomeProduto)){
				return produto;
			}
		}
		throw new ProdutoException(Constantes.PRODUTO_NAO_EXISTE);
	}
	
	private static List<TipoProduto> inicializaListaTiposProdutos() {
		List<TipoProduto> listaTiposProduto = new ArrayList<TipoProduto>();
		listaTiposProduto.add(TipoProduto.ALIMENTO);
		listaTiposProduto.add(TipoProduto.BEBIDA);
		listaTiposProduto.add(TipoProduto.CARNE);
		listaTiposProduto.add(TipoProduto.LIMPEZA);
		return listaTiposProduto;
	}

}
