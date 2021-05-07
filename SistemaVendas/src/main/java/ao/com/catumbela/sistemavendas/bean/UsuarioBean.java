package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.PessoaDAO;
import ao.com.catumbela.sistemavendas.dao.UsuarioDAO;
import ao.com.catumbela.sistemavendas.domain.Pessoa;
import ao.com.catumbela.sistemavendas.domain.Usuario;
import lombok.Data;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Data

public class UsuarioBean implements Serializable {
	private Usuario usuario;
	
	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;
	
	
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	public void novo() {
		try {
			usuario = new Usuario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			usuario = new Usuario();
			usuarios = usuarioDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(Usuario usuarioSelecionado) {
		try {
			usuario = usuarioSelecionado;
			
		} catch (Exception erro) {
			
		}
	}
	
	public void editar(Usuario usuarioSelecionado) {
		usuario = usuarioSelecionado;
	}

}
