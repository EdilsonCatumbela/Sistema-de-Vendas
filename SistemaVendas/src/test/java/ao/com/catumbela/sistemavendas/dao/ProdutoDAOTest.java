package ao.com.catumbela.sistemavendas.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ao.com.catumbela.sistemavendas.domain.Produto;
import ao.com.catumbela.sistemavendas.domain.Fornecedor;

public class ProdutoDAOTest {
	private ProdutoDAO produtoDAO;

	@Before
	public void iniciar() {
		produtoDAO = new ProdutoDAO();
	}

	public void salvar() {
		Long codigo =2L;
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscar(codigo);
		Produto produto = new Produto();
		//provincia.setCodigo(1);
		produto.setDescricao("Roupas");;
		produto.setFornecedor(fornecedor);
		produto.setPreco(new BigDecimal("190.56"));
		produto.setQuantidade(new Short("2"));
		
		

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

	}
	public void buscar() {
		Long codigo =2L;
		Produto municipio = produtoDAO.buscar(codigo);
		System.out.println(municipio);

	}
	public void listar() {

		List<Produto> produtos = produtoDAO.listar();

		for (Produto produto : produtos) {
			System.out.println(produto.getCodigo()+ " - " + produto.getDescricao() + " - " + produto.getFornecedor().getCogido()+ " - " + produto.getPreco()+ " - " + produto.getQuantidade());
			
		}
	
	}
	
	public void excluir() {
		Long codigo =2L;
		//Municipio municipio = municipioDAO.buscar(2);
		produtoDAO.excluir(codigo);
		
 
	}

	@Test
	public void testar() {
		///salvar();
		buscar();
		//excluir();
		//listar();
		

	}


}
