package ao.com.catumbela.sistemavendas.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;


public class JPAUtil {
	public static SessionFactory fabricaDeSessoes;

	private static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaVendasPU");
	public static EntityManager getEntityManager() {
		EntityManager sessao = fabrica.createEntityManager();
		return sessao;
	}

	
	public static Connection getConexao() {
		//Session session = fabrica.unwrap(Session.class);
		//fabricaDeSessoes = session.getSessionFactory();
		Session sessao = fabricaDeSessoes.openSession();
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection connection) throws SQLException {
				
				return connection;
			}
		});
		
	return conexao;
	}
	
}
