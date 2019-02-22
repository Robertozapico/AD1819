package ej02unidiranotaciones;

import mapeo.Direccion;
import mapeo.Persona;
import objetoDao.ContactoDao;
import org.hibernate.Session;

/**
 *
 * @author Elvis
 */
public class Ej02UniDirAnotaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContactoDao contactoDao = new ContactoDao();
        
        
    Persona persona2 = new Persona();
    persona2.setNombre("Roberto");
    
    Direccion direccion2 = new Direccion();
    direccion2.setCalle("Calle 2");
    direccion2.setCodigoPostal("54321");
    persona2.setDireccion(direccion2);

    contactoDao.guardarPersona(persona2);
    persona2.setId(2);
    //persona2.setNombre("Zapico");
    //contactoDao.actualizaContacto(persona2);
    //contactoDao.eliminarDireccion(direccion2);
    contactoDao.eliminaContacto(persona2);

    
    
    }

}
