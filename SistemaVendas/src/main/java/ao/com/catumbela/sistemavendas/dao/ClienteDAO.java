package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Cliente;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class ClienteDAO {
	public void salvar(Cliente cliente) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(cliente);
		transacao.commit();
		sessao.close();
		
	}
	public Cliente buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Cliente cliente = sessao.find(Cliente.class, codigo);
		return cliente;

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		String jpql = "select p from Cliente p order by pessoa";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Cliente> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Cliente cliente= sessao.find(Cliente.class, codigo);
		sessao.remove(cliente);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Cliente cliente) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(cliente);
		transacao.commit();
		sessao.close();
	}
	
	public void merge(Cliente cliente) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(cliente);
		transacao.commit();
		sessao.close();
		
	}


}
