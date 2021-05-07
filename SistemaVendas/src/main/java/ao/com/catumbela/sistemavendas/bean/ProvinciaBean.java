package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.ProvinciaDAO;
import ao.com.catumbela.sistemavendas.domain.Provincia;
import lombok.Data;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Data
public class ProvinciaBean implements Serializable {
	private Provincia provincia;
	private List<Provincia> provincias;

	private ProvinciaDAO provinciaDAO;

	@PostConstruct
	public void listar() {
		try {
			provinciaDAO = new ProvinciaDAO();

			provincia = new Provincia();
			provincias = provinciaDAO.listar();
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as províncias");
			erro.printStackTrace();
		}
		
	}

	public void novo() {
		provincia = new Provincia();
	}

	public void salvar() {
		try {
			provinciaDAO.merge(provincia);

			provincia = new Provincia();
			provincias = provinciaDAO.listar();
			Messages.addGlobalInfo("Província salva com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a província");
			erro.printStackTrace();
		}
		

	}
	public void excluir(Provincia produtoSelecionado) {
		try {
			provincia = produtoSelecionado;
			ProvinciaDAO provinciaDAO = new ProvinciaDAO(); 
			provinciaDAO.excluir(provincia.getCodigo());
			provincias = provinciaDAO.listar();
			
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a província");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(Provincia produtoSelecionado) {
		provincia = produtoSelecionado;
		
		
	}
}
