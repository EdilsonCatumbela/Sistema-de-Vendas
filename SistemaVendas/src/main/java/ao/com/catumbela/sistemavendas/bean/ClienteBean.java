package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.ClienteDAO;
import ao.com.catumbela.sistemavendas.dao.PessoaDAO;
import ao.com.catumbela.sistemavendas.domain.Cliente;
import ao.com.catumbela.sistemavendas.domain.Pessoa;
import lombok.Data;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Data
public class ClienteBean implements Serializable {
	private Cliente cliente;

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			Messages.addFlashGlobalInfo("Pessoa salva com sucesso");
			clientes = clienteDAO.listar();

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}

	}

	public void excluir(Cliente clienteSelecionado) {
		try {
			cliente = clienteSelecionado;

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente.getCodigo());
			
			Messages.addFlashGlobalInfo("Pessoa excluida com sucesso");

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o cliente");
			erro.printStackTrace();

		}

	}

	public void editar(Cliente clienteSelecionado) {
		cliente = clienteSelecionado;

		
	}

}
