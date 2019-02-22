/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetoDao;

import ej02unidiranotaciones.HibernateUtil;
import mapeo.Direccion;
import mapeo.Persona;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumnop
 */
public class ContactoDao {

    private Session sesion;
    private Transaction tx;

    public void guardarPersona(Persona persona) {
        if (persona != null) {
            iniciarOperacion();
            sesion.persist(persona);
            sesion.getTransaction().commit();
            finOperacion();
        }

    }

    private void iniciarOperacion() {
        sesion = HibernateUtil.getSession().openSession();
        tx = sesion.beginTransaction();
    }

    private void finOperacion() {
        sesion.close();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public void actualizaContacto(Persona persona) throws HibernateException {
        try {
            iniciarOperacion();
            sesion.update(persona);
            tx.commit();
            
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }
    //tengo que borrar la direccion
    public void eliminaContacto(Persona persona) throws HibernateException 
{ 
    try 
    { 
        iniciarOperacion(); 
        sesion.delete(persona); 
        tx.commit(); 
    } catch (HibernateException he) 
    { 
        manejaExcepcion(he); 
        throw he; 
    }finally 
    { 
        sesion.close(); 
    } 
}
    
    public void eliminarDireccion(Direccion direccion){
        iniciarOperacion();
        sesion.delete(direccion);
        tx.commit();
    }

}
