package ej04bidiranotaciones;

import ej04bidiranotaciones.HibernateUtil;
import static ej04bidiranotaciones.HibernateUtil.getSessionFactory;
import mapeo.Pais;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author victoriarodriguez
 */
public class PaisDAO {

       private Session sesion;
       private Transaction tx;

       public void guardaContacto(Pais pais) throws HibernateException {
              

              try {  
                     startOperation();
                     System.out.println(pais.getNombre());
                      sesion.persist(pais);
                     sesion.getTransaction().commit();
                     sesion.close();
              } catch (HibernateException he) {
                          manejaExcepcion(he); 
                     throw he;
              } finally {
                     
                  //   finOperation();
              }

          
       }

     //         Session sesion = HibernateUtil.getSessionFactory().openSession();
     //         sesion.beginTransaction();
     //         sesion.persist(pais);
     //         sesion.getTransaction().commit();
     //         sesion.close();
    private void startOperation() {
        //opcion 1: abre una nueva sesionFactory
       sesion = HibernateUtil.getSessionFactory().openSession();
       sesion.beginTransaction(); 
       // tx= sesion.getTransaction().begin();//permite que comience la transacion
    }
    private void finOperation() {
  sesion.close();

    }
       private void manejaExcepcion(HibernateException he) throws HibernateException {
              sesion.getTransaction().rollback();
              throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
       }

}
