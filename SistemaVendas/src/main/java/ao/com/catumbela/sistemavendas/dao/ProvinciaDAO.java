package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Provincia;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class ProvinciaDAO {
	public void salvar(Provincia provincia) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(provincia);
		transacao.commit();
		sessao.close();
		
	}
	public Provincia buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Provincia provincia = sessao.find(Provincia.class, codigo);
		return provincia;

	}

	@SuppressWarnings("unchecked")
	public List<Provincia> listar() {
		String jpql = "select p from Provincia p order by nome";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Provincia> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Provincia provincia = sessao.find(Provincia.class, codigo);
		sessao.remove(provincia);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Provincia provincia) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(provincia);
		transacao.commit();
		sessao.close();
	}
	public void merge(Provincia provincia) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(provincia);
		transacao.commit();
		sessao.close();
		
	}
	

}
