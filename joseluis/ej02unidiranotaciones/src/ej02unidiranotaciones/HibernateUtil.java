package ej02unidiranotaciones;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author alumnop
 */
public class HibernateUtil {

    private final static SessionFactory SESSIONFACTORY;

    static {
        try {
            /*SESSIONFACTORY = new AnnotationConfiguration().configure()
            .addAnnotatedClass(Persona.class)
            .addAnnotatedClass(Direccion.class)
            .buildSessionFactory(); */
            Configuration configuration = new Configuration()
                    .configure(); // configures settings from hibernate.cfg.xml

            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

            // If you miss the below line then it will complaing about a missing dialect setting
            serviceRegistryBuilder.applySettings(configuration.getProperties());

            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            SESSIONFACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException he) {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    public static SessionFactory getSession() {
        return SESSIONFACTORY;
    }

}
