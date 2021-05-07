package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.MunicipioDAO;
import ao.com.catumbela.sistemavendas.dao.PessoaDAO;
import ao.com.catumbela.sistemavendas.dao.ProvinciaDAO;
import ao.com.catumbela.sistemavendas.domain.Municipio;
import ao.com.catumbela.sistemavendas.domain.Pessoa;
import ao.com.catumbela.sistemavendas.domain.Provincia;
import lombok.Data;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Data
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Provincia provincia;
	private List<Provincia> provincias;
	private List<Municipio> municipios;

	@PostConstruct
	public void listar() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar(); // imprimir

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();

		}

	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			provincia = new Provincia();

			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			provincias = provinciaDAO.listar();

			municipios = new ArrayList<>();

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerrar uma nova pessoa");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar();

			pessoa = new Pessoa();
			provincia = new Provincia();

			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			provincias = provinciaDAO.listar();

			municipios = new ArrayList<>();

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar uma nova pessoa");
			erro.printStackTrace();
		}

	}

	public void excluir(Pessoa pessoaSelecionada) {
		try {
			pessoa = pessoaSelecionada;
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa.getCodigo());
			
			pessoa = new Pessoa();
			provincia = new Provincia();

			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			provincias = provinciaDAO.listar();

			municipios = new ArrayList<>();
			
			Messages.addFlashGlobalInfo("Pessoa excluida com sucesso");
			
			pessoas = pessoaDAO.listar();
			

		} catch (Exception erro) {
			Messages.addFlashGlobalInfo("Ocorreu um erro ao tentar excluir um município");
			erro.printStackTrace();
		}

	}

	public void editar(Pessoa pessoaSelecionada) {
		try {
			pessoa = pessoaSelecionada;
			provincia = pessoa.getMunicipio().getProvincia();
			
			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			provincias = provinciaDAO.listar();
			
			MunicipioDAO municipioDAO = new MunicipioDAO();
			municipios = municipioDAO.buscarPorProvincia(provincia.getCodigo());
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
		}
		
	}

	public void popular() {
		try {
			if (provincia != null) {
				MunicipioDAO municipioDAO = new MunicipioDAO();
				municipios = municipioDAO.buscarPorProvincia(provincia.getCodigo());

			} else {
				municipios = new ArrayList<>();
			}

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtar as cidades");
			erro.printStackTrace();

		}

	}

}
