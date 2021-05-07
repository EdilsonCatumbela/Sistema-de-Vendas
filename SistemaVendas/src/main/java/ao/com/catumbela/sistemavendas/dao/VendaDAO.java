package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.ItemVenda;
import ao.com.catumbela.sistemavendas.domain.Venda;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class VendaDAO {
	public void salvar(Venda venda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(venda);
		transacao.commit();
		sessao.close();
		
	}
	public Venda buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Venda produto = sessao.find(Venda.class, codigo);
		return produto;

	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		String jpql = "select p from Venda p order by cliente";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Venda> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Venda produto= sessao.find(Venda.class, codigo);
		sessao.remove(produto);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Venda venda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(venda);
		transacao.commit();
		sessao.close();
	}
	
	public void salvar(Venda venda, List<ItemVenda> itensVenda) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		try {
			
			transacao.begin();
			sessao.persist(venda);
			
			for(int posicao =0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.persist(itemVenda);
			}
			
			transacao.commit();
		
			
		} catch (RuntimeException erro) {
			if(transacao !=null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
		
		
	}


}
