package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.FornecedorDAO;
import ao.com.catumbela.sistemavendas.domain.Fornecedor;
import lombok.Data;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Data
public class FornecedorBean implements Serializable {
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private FornecedorDAO fornecedorDAO;

	@PostConstruct
	public void listar() {
		try {
			fornecedorDAO = new FornecedorDAO();

			fornecedor = new Fornecedor();
			fornecedores = fornecedorDAO.listar();
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fornenecedor");
			erro.printStackTrace();
		}

	}

	public void novo() {
		fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			fornecedorDAO.merge(fornecedor);

			fornecedor = new Fornecedor();
			fornecedores = fornecedorDAO.listar();
			Messages.addGlobalInfo("Fornecedor salvo com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o fornecedor");
			erro.printStackTrace();
		}
	}

	public void excluir(Fornecedor fornecedorSelecionado) {
		try {
			fornecedor = fornecedorSelecionado;
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.excluir(fornecedor.getCogido());
			fornecedores = fornecedorDAO.listar();
			
		} catch (Exception erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar excluir o fornecedor");
			erro.printStackTrace();
		}
		
	}
	public void editar(Fornecedor fornecedorSelecionado) {
		fornecedor = fornecedorSelecionado;
	}

}
