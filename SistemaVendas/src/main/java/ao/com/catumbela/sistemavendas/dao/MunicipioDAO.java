package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Municipio;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class MunicipioDAO {
	
	public void salvar(Municipio municipio) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(municipio);
		transacao.commit();
		sessao.close();
		
	}
	public Municipio buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Municipio municipio = sessao.find(Municipio.class, codigo);
		return municipio; 

	}

	@SuppressWarnings("unchecked")
	public List<Municipio> listar() {
		String jpql = "select p from Municipio p order by nome";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Municipio> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Municipio municipio= sessao.find(Municipio.class, codigo);
		sessao.remove(municipio);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Municipio municipio) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(municipio);
		transacao.commit();
		sessao.close();
	}
	public void merge(Municipio municipio) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(municipio);
		transacao.commit();
		sessao.close();
		 
	}
	
	@SuppressWarnings("unchecked")
	public List<Municipio> buscarPorProvincia(Long provinciaCodigo){
			
		String jpql = "select p from Municipio p where provincia like '"+provinciaCodigo+"' order by nome";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Municipio> resultado = consulta.getResultList();
		return resultado;
	
	}

}
