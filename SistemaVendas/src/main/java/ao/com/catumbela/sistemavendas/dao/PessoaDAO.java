package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Pessoa;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class PessoaDAO {
	public void salvar(Pessoa pessoa) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(pessoa);
		transacao.commit();
		sessao.close();

	}

	public Pessoa buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Pessoa pessoa = sessao.find(Pessoa.class, codigo);
		return pessoa;

	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() {
		String jpql = "select p from Pessoa p order by nome";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Pessoa> resultado = consulta.getResultList();
		return resultado;

	}

	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Pessoa pessoa = sessao.find(Pessoa.class, codigo);
		sessao.remove(pessoa);
		transacao.commit();
		sessao.close();
	}

	public void editar(Pessoa pessoa) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(pessoa);
		transacao.commit();
		sessao.close();
	}

	public void merge(Pessoa pessoa) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(pessoa);
		transacao.commit();
		sessao.close();
	}

}
