/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1hibernate;

import java.util.Date;

/**
 *
 * @author alumnop
 */
public class Ejercicio1Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LogicaMetodos logica = new LogicaMetodos();
        
        Contacto contacto = new Contacto();
        contacto.setNombre("Ricardo");
        contacto.setEmail("prueba@gmail.com");
        contacto.setTelefono("5435345");
        
        logica.guardaContacto(contacto);

        /*logica.guardaActor(actor);
        
        
        actor.setLastName("Zapi");
        logica.actualizaActor(actor);
        logica.eliminaActor(actor);
        actor = logica.obtenActor(id);
        System.out.println(actor.getFirstName());
        System.out.println(logica.obtenListaActores());
        
        
        daoActores.guardaActor(actor);
        actor.setLastName("ZapiCo");
        daoActores.actualizaActor(actor);
        logica.eliminaActor(actor);
        actor = daoActores.obtenActor(id);
        System.out.println(actor.getFirstName());
        System.out.println(daoActores.obtenListaActor());
         */
        //ARRIBA FUNCIONA
    }
    
}
