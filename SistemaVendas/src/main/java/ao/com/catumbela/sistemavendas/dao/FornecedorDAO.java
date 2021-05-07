package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Fornecedor;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class FornecedorDAO {
	public void salvar(Fornecedor fornecedor) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(fornecedor);
		transacao.commit();
		sessao.close();
		
	}
	public Fornecedor buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Fornecedor fornecedor = sessao.find(Fornecedor.class, codigo);
		return fornecedor;

	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() {
		String jpql = "select p from Fornecedor p order by descricao";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Fornecedor> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Fornecedor fornecedor = sessao.find(Fornecedor.class, codigo);
		sessao.remove(fornecedor);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Fornecedor fornecedor) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(fornecedor);
		transacao.commit();
		sessao.close();
	}
	
	public void merge(Fornecedor fornecedor) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(fornecedor);
		transacao.commit();
		sessao.close();
		
	}


}
