/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examendistancia;

import examendistancia.mapeo.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author alumnop
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //CONFIGURACION SIEMPRE EN EL DEFAULT PACKAGE PORQUE SINO DA ERROR DE QUE NO ENCUENTRA EL FICHERO DE CONFIGURACION
        Session openSession = HibernateUtil.getSession().openSession();
        openSession.beginTransaction();
        
    }
    
}
