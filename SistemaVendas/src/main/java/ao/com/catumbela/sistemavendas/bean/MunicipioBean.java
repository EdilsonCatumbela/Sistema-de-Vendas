package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.MunicipioDAO;
import ao.com.catumbela.sistemavendas.dao.ProvinciaDAO;
import ao.com.catumbela.sistemavendas.domain.Municipio;
import ao.com.catumbela.sistemavendas.domain.Provincia;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Getter
@Setter
public class MunicipioBean implements Serializable {
	private Municipio municipio;
	private List<Municipio> municipios;
	private MunicipioDAO municipioDAO;
	private List<Provincia> provincias;
	private ProvinciaDAO provinciaDAO;

	@PostConstruct
	public void listar() {
		provinciaDAO = new ProvinciaDAO();
		
		MunicipioDAO municipioDAO = new MunicipioDAO();
		provinciaDAO = new ProvinciaDAO();
		municipios = municipioDAO.listar();
		provincias = provinciaDAO.listar();
	}


	public void novo() {
		municipio = new Municipio();
		
		provincias = provinciaDAO.listar();
	}

	public void salvar() {
		try {
			
			MunicipioDAO municipioDAO = new MunicipioDAO();
			municipioDAO.merge(municipio);
			municipio = new Municipio();
			provinciaDAO = new ProvinciaDAO();
			municipios = municipioDAO.listar();
			provincias = provinciaDAO.listar();

			Messages.addFlashGlobalInfo("Município salvo com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo município");
			erro.printStackTrace();
		}
	}


	public void excluir(Municipio municipioSelecionado) {
		try {
			municipio = municipioSelecionado;
			MunicipioDAO municipioDAO = new MunicipioDAO();
			municipioDAO.excluir(municipio.getCodigo());
			Messages.addFlashGlobalInfo("Município exluido com sucesso");
			
			municipios = municipioDAO.listar();
		} catch (Exception erro) {
			Messages.addFlashGlobalInfo("Ocorreu um erro ao tentar excluir um município");
			erro.printStackTrace();
		}

	}

	public void editar(Municipio selecionarMunicipio) {
		municipio = selecionarMunicipio;
	}
}