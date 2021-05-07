package ao.com.catumbela.sistemavendas.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		JPAUtil.getEntityManager().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		JPAUtil.getEntityManager();
	}

}
