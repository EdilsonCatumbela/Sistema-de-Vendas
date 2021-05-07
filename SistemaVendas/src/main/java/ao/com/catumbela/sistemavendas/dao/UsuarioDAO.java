package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Usuario;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class UsuarioDAO {
	public void salvar(Usuario usuario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(usuario);
		transacao.commit();
		sessao.close();
		
	}
	public Usuario buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Usuario usuario = sessao.find(Usuario.class, codigo);
		return usuario;

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		String jpql = "select p from Usuario p order by pessoa";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Usuario> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Usuario usuario= sessao.find(Usuario.class, codigo);
		sessao.remove(usuario);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Usuario usuario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(usuario);
		transacao.commit();
		sessao.close();
	}
	public void merge(Usuario usuario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(usuario);
		transacao.commit();
		sessao.close();
		
	}


}
