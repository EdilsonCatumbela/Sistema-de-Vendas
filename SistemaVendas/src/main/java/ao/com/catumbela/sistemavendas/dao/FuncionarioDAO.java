package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Funcionario;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class FuncionarioDAO {
	public void salvar(Funcionario funcionario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(funcionario);
		transacao.commit();
		sessao.close();
		
	}
	public Funcionario buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Funcionario funcionario = sessao.find(Funcionario.class, codigo);
		return funcionario;

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		String jpql = "select p from Funcionario p order by pessoa";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Funcionario> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Funcionario funcionario= sessao.find(Funcionario.class, codigo);
		sessao.remove(funcionario);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Funcionario funcionario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(funcionario);
		transacao.commit();
		sessao.close();
	}
	public void merge(Funcionario funcionario) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(funcionario);
		transacao.commit();
		sessao.close();
		
	}


}
