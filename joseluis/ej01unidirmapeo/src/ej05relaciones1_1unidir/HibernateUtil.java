package ej05relaciones1_1unidir;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Elvis
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
    private static Transaction tx;
    private static Session sesion;
    
static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            
                sessionFactory = new Configuration().configure().buildSessionFactory(); 
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
}
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Se encarga de iniciar una transaccion de la base de datos
     * Metodo startoperation
     */
    private static void startOperation() {
        //opcion 1: abre una nueva sesionFactory
        SessionFactory sessionFactory = getSessionFactory();
        sesion = sessionFactory.openSession();//le pasa a la sesion antes declarada la sesonFactory y la abre
        sesion.getTransaction().begin();//permite que comience la transacion
    }
    
    /**
     * Se encarga de terminar una transaccion de la base de datos
     * Metodo terminaOperacion
     */
    private static void finishOperation() {
        sesion.getTransaction().commit();//confirma la transacion
        sesion.close();//cierra la sesion
    }
    
}
