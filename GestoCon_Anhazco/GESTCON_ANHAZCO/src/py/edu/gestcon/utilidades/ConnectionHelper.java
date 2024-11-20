package py.edu.gestcon.utilidades;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectionHelper {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) setUp(); 	
		return sessionFactory;

	}
	public static void setUp() {
		final StandardServiceRegistry registry=
				new StandardServiceRegistryBuilder()
				.configure()
				.build();

		try {
			sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}

	}
}
