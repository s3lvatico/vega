package org.gmnz.vega.base;


import org.gmnz.vega.integration.AllergenEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

	private static SessionFactory sessionFactory;



	public void bootstrap() {


		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("/hibernate.cfg.xml")
				.build();

		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(AllergenEntity.class)
				.getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
				.build();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();


		sessionFactory = sessionFactoryBuilder.build();


	}



	public void shutdown() {
		sessionFactory.close();
	}



	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
