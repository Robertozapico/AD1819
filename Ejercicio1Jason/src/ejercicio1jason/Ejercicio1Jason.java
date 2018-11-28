/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1jason;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnop
 */
public class Ejercicio1Jason {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        jSonMetodos metodos = new jSonMetodos();
        /*try {
            metodos.crearFicheros("ficheroJSON.json");
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1Jason.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println("cantidad de libros:" + metodos.cantidadDeLibros());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1Jason.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(metodos.titulosDeLibros(metodos.informacionJson()));
            //metodos.titulosDeLibros(metodos.informacionJson());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1Jason.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            metodos.autorLibro(2, 5, metodos.informacionJson());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1Jason.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        try {
            System.out.println(metodos.valorLibrosEnStock(metodos.informacionJson()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1Jason.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
