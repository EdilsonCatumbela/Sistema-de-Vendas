package ao.com.catumbela.sistemavendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ao.com.catumbela.sistemavendas.domain.Produto;
import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class ProdutoDAO {
	public void salvar(Produto produto) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		sessao.persist(produto);
		transacao.commit();
		sessao.close();
		
	}
	public Produto buscar(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		Produto produto = sessao.find(Produto.class, codigo);
		return produto;

	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		String jpql = "select p from Produto p order by descricao";
		EntityManager sessao = JPAUtil.getEntityManager();
		Query consulta = sessao.createQuery(jpql);
		List<Produto> resultado = consulta.getResultList();
		return resultado;

	}
	
	public void excluir(Long codigo) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Produto produto= sessao.find(Produto.class, codigo);
		sessao.remove(produto);
		transacao.commit();
		sessao.close();
	}
	
	public void editar(Produto produto) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin(); 
		sessao.merge(produto);
		transacao.commit();
		sessao.close();
	}
	public Produto merge(Produto produto) {
		EntityManager sessao = JPAUtil.getEntityManager();
		EntityTransaction transacao = sessao.getTransaction();
		transacao.begin();
		Produto retorno = sessao.merge(produto);
		transacao.commit();
		sessao.close();
		return retorno;
		
		
	}

}
