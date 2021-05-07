package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.FuncionarioDAO;
import ao.com.catumbela.sistemavendas.dao.PessoaDAO;
import ao.com.catumbela.sistemavendas.domain.Funcionario;
import ao.com.catumbela.sistemavendas.domain.Pessoa;
import lombok.Data;

@SuppressWarnings("serial")

@Named
@ViewScoped
@Data
public class FuncionarioBean implements Serializable {
	private Funcionario funcionario;
	
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;
	
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu a um erro ao tentar listar os funcionários");
		}
	}
	
	public void novo() {
		try {
			funcionario = new Funcionario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo funcionário");
		}
	}
	
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.merge(funcionario);
		
		funcionario = new Funcionario();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoas = pessoaDAO.listar();

		Messages.addFlashGlobalInfo("Funcionário salva com sucesso");
		funcionarios = funcionarioDAO.listar();
	
	}
	
	public void excluir(Funcionario funcionarioSelecionado) {
		try {
			funcionario = funcionarioSelecionado;
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario.getCodigo());
			
			Messages.addGlobalInfo("Funcionário excluido com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o funcionário");
			erro.printStackTrace();
		}	
		
	}
	
	public void editar(Funcionario funcionarioSelecionado) {
		funcionario = funcionarioSelecionado;
		
	}

}
