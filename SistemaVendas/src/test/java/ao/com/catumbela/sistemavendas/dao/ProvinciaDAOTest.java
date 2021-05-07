package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ao.com.catumbela.sistemavendas.domain.Provincia;

public class ProvinciaDAOTest {
	private ProvinciaDAO provinciaDAO;

	@Before
	public void iniciar() {
		provinciaDAO = new ProvinciaDAO();
	}

	public void salvar() {
		Provincia provincia = new Provincia();
		//provincia.setCodigo(1);
		provincia.setNome("Benguela");
		provincia.setSigle("BA");

		Provincia provincia2 = new Provincia();
		//provincia2.setCodigo(2);
		provincia2.setNome("Uige");
		provincia2.setSigle("UE");

		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		provinciaDAO.salvar(provincia);
		provinciaDAO.salvar(provincia2);

	}

	public void buscar() {
		Long codigo = 1L;
		Provincia provincia = provinciaDAO.buscar(codigo);
		System.out.println(provincia);

		Provincia provincia2 = provinciaDAO.buscar(codigo);
		System.out.println(provincia2);

	}


	public void listar() {
		List<Provincia> provincias = provinciaDAO.listar();

		for (Provincia provincia : provincias) {
			System.out.println(provincia.getCodigo() + " - " + provincia.getNome() + " - " + provincia.getSigle());
			
		}
	}
	public void excluir() {
		Long codigo =1L;
		Provincia provincia = provinciaDAO.buscar(codigo);
		
		
	}

	@Test
	public void testar() {
		salvar();
		buscar();
		listar();

	}

}
