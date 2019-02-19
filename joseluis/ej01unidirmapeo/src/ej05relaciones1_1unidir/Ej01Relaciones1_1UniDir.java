package ej05relaciones1_1unidir;

import mapeo.Direccion;
import mapeo.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alumno
 */
public class Ej01Relaciones1_1UniDir {

       /**
        * @param args the command line arguments
        */
       public static void main(String[] args) {
              Persona persona1 = new Persona();
              persona1.setNombre("Persona que sera borrada");

              Persona persona2 = new Persona();
              persona2.setNombre("Persona que permanecera");

              Direccion direccion1 = new Direccion();
              direccion1.setCalle("Calle 1");
              direccion1.setCodigoPostal("12345");

              Direccion direccion2 = new Direccion();
              direccion2.setCalle("Calle 2");
              direccion2.setCodigoPostal("54321");

              persona1.setDireccion(direccion1);
              persona2.setDireccion(direccion2);
              
              SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

              Session sesion = sessionFactory.openSession();

              /*Esta direccion se agrega para comprobar que las personas tomen el mismo 
    identificador que las direcciones (ninguna persona debe tener el mismo id de
    esta direccion)*/
              Direccion direccion3 = new Direccion();
              direccion3.setCalle("Calle de Prueba de identificadores");
              direccion3.setCodigoPostal("21345");

              /*En la primer sesion a la base de datos almacenamos los dos objetos Persona 
    los objetos Direccion se almacenaran en cascada*/
              sesion.beginTransaction();

              sesion.persist(direccion3);
              sesion.persist(persona1);
              sesion.persist(persona2);

              sesion.getTransaction().commit();
              sesion.close();


              /*En la segunda sesion eliminamos el objeto persona1, 
    la direccion1 sera borrada en cascada*/
      //        SessionFactory sessionFactory2 = new AnnotationConfiguration().configure().buildSessionFactory();

              Session sesion2 = sessionFactory.openSession();
              sesion2.beginTransaction();

              sesion2.delete(persona1);

              sesion2.getTransaction().commit();
              sesion2.close();
       }

}
