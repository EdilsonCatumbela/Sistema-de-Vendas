package ao.com.catumbela.sistemavendas.util;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JPAUtilTest {
	@Test
	public void getEntityManager() {
		EntityManager sessao = JPAUtil.getEntityManager();
		sessao.close();
	}

}
