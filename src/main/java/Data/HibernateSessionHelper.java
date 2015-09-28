package data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URI;

public class HibernateSessionHelper {

	private static SessionFactory sessionFactory;

	public static void configHibernate() {
		try {
			Configuration cfg = new Configuration().configure();
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

			cfg.setProperty("hibernate.connection.url", dbUrl);
			cfg.setProperty("hibernate.connection.username", username);
			cfg.setProperty("hibernate.connection.password", password);

			sessionFactory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}