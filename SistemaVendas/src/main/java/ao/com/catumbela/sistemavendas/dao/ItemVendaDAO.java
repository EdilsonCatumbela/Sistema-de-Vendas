package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.ItemVenda;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class ItemVendaDAO {
	public void salvar(ItemVenda itemVenda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(itemVenda);
		transacao.commit();
		sessao.close();

	}

	public ItemVenda buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		ItemVenda itemVenda = sessao.find(ItemVenda.class, codigo);
		return itemVenda;

	}

	@SuppressWarnings("unchecked")
	public List<ItemVenda> listar() {
		String jpql = "select p from ItemVenda p order by produto";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<ItemVenda> resultado = consulta.getResultList();
		return resultado;

	}

	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		ItemVenda itemVenda = sessao.find(ItemVenda.class, codigo);
		sessao.remove(itemVenda);
		transacao.commit();
		sessao.close();
	}

	public void editar(ItemVenda itemVenda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(itemVenda);
		transacao.commit();
		sessao.close();
	}

	public void merge(ItemVenda itemVenda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.merge(itemVenda);
		transacao.commit();
		sessao.close();

	}

}
