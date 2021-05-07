package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ao.com.catumbela.sistemavendas.domain.Municipio;
import ao.com.catumbela.sistemavendas.domain.Provincia;

public class MunicipioDAOTest {
	private MunicipioDAO municipioDAO;

	@Before
	public void iniciar() {
		municipioDAO = new MunicipioDAO();
	}

	public void salvar() {
		Long codigo =2L;
		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		Provincia provincia = provinciaDAO.buscar(codigo);
		Municipio municipio = new Municipio();
		//provincia.setCodigo(1);
		municipio.setNome("Matala");
		municipio.setProvincia(provincia);

		MunicipioDAO municipioDAO = new MunicipioDAO();
		municipioDAO.salvar(municipio);

	}
	public void buscar() {
		Long codigo =2L;
		Municipio municipio = municipioDAO.buscar(codigo);
		System.out.println(municipio);

	}
	public void listar() {

		List<Municipio> municipios = municipioDAO.listar();

		for (Municipio municipio : municipios) {
			System.out.println(municipio.getCodigo() + " - " + municipio.getNome() + " - " + municipio.getProvincia().getCodigo());
			
		}
		
	}
	
	public void excluir() {
		Long codigo =2L;
		//Municipio municipio = municipioDAO.buscar(codigo);
		municipioDAO.excluir(2L);
 
	}
	
	public void buscarPorProvincia() {
		Long provinciaCodigo = 1L;
		
		MunicipioDAO municipioDAO = new MunicipioDAO();
		List<Municipio> municipios = municipioDAO.buscarPorProvincia(provinciaCodigo);
		
		for (Municipio municipio : municipios) {
			System.out.println("Código do municipio: "+municipio.getCodigo());
			System.out.println("Nome do municipio: " + municipio.getNome());
			System.out.println("Código da Província: "+ municipio.getProvincia().getCodigo());
		
		}
		
	}

	@Test
	public void testar() {
		buscarPorProvincia();
		
		//salvar();
		//buscar();
		//excluir();
		//listar(); 
		

	}
	
	
	

}
