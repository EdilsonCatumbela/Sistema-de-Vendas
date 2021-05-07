package ao.com.catumbela.sestemavendas.main;

import javax.persistence.EntityManager;

import ao.com.catumbela.sistemavendas.util.JPAUtil;

public class TestaConexao {

	public static void main(String[] args) {
		EntityManager sessao = JPAUtil.getEntityManager();
		sessao.close();

	}

}
