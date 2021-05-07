package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ao.com.catumbela.sistemavendas.domain.Fornecedor;

public class FornecedorDAOTest {
	private FornecedorDAO fornecedorDAO;

	@Before
	public void iniciar() {
		fornecedorDAO = new FornecedorDAO();
	}

	public void salvar() {
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setDescricao("Edilson Fonseca");

		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setDescricao("Joana Sambundo");
		;

		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedorDAO.salvar(fornecedor);
		fornecedorDAO.salvar(fornecedor2);

	}

	public void buscar() {
		Long codigo =2L;
		Fornecedor fornecedor = fornecedorDAO.buscar(codigo);
		System.out.println(fornecedor);

		Fornecedor fornecedor2 = fornecedorDAO.buscar(codigo);
		System.out.println(fornecedor2);

	}

	public void listar() {
		List<Fornecedor> fornecedores = fornecedorDAO.listar();

		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor.getCogido() + " - " + fornecedor.getDescricao());

		}
	}

	public void excluir() {
		Long codigo = 1L;
		//Fornecedor fornecedor = fornecedorDAO.buscar(codigo);
		fornecedorDAO.excluir(codigo);

	}

	@Test
	public void testar() {
		salvar();
		//buscar();
		//listar();
		//excluir();

	}

}
