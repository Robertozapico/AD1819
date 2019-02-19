package ej04bidiranotaciones;

import mapeo.Pais;
import mapeo.Presidente;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Elvis
 */
public class Ej04BiDirAnotaciones {

       /**
        * @param args the command line arguments
        */
       public static void main(String[] args) {
              Pais pais1 = new Pais();
              pais1.setNombre("China");

              Pais pais2 = new Pais();
              pais2.setNombre("Corea");

              Presidente presidente1 = new Presidente();
              presidente1.setNombre("Jiang Zemin");

              Presidente presidente2 = new Presidente();
              presidente2.setNombre("Kim Dae-Jung");

              pais1.setPresidente(presidente1);
              pais2.setPresidente(presidente2);

              presidente1.setPais(pais1);
              presidente2.setPais(pais2);

              Session sesion = HibernateUtil.getSessionFactory().openSession();

              /*Este pais se agrega para comprobar que los presidentes tomen el mismo  
    identificador que los paises*/
              Pais p = new Pais();
              p.setNombre("Chipre");

              /*En la primer sesion a la base de datos almacenamos los dos objetos Pais  
    los objetos Presidente se almacenaran en cascada*/
              sesion.beginTransaction();

              sesion.persist(p);
              sesion.persist(pais1);
              sesion.persist(pais2);

              sesion.getTransaction().commit();
              sesion.close();

              /*En la segunda sesion eliminamos el objeto pais1,  
    el presidente1 sera borrado en cascada*/
              sesion = HibernateUtil.getSessionFactory().openSession();
              sesion.beginTransaction();

              sesion.delete(pais1);

              sesion.getTransaction().commit();
              sesion.close();

              // DAO
              Pais pais3 = new Pais();
              pais3.setNombre("USA");

              Presidente presidente3 = new Presidente();
              presidente3.setNombre("Trump");

              pais3.setPresidente(presidente3);
              presidente3.setPais(pais3);

              // persistencia sin DAO
/*              Session sesion2 = HibernateUtil.getSessionFactory().openSession();
              sesion2.beginTransaction();
              sesion2.persist(pais3);
              sesion2.getTransaction().commit();
              sesion2.close();
              */              
              
              PaisDAO paisDAO = new PaisDAO();

              try {

                     System.out.println("AAAA");
                    paisDAO.guardaContacto(pais3);
              } catch (HibernateException hibernateException) {
                     System.err.println("Error DAO:" + hibernateException.getMessage());
              }

       }

}
