/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnop
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] lista = null;
        File[] archivos = null;
        OperacionesFicheros of = new OperacionesFicheros();
        Cifrado cifrar = new Cifrado();
        QuijoteLogica logicaQuijote = new QuijoteLogica();
        //Filtros
        //prueba de que el ejercicio 1.A funciona entero
        //of.ListarFicheros("/home/alumnop/Carpetavacia/docu.txt", true, true);

        //prueba de que el ejercicio 1.B funciona entero
        /*ArrayList<String> listaDirectorios = new ArrayList<String>();
        
        listaDirectorios.add("Nueva");
        listaDirectorios.add("Vacia");
        listaDirectorios.add("Prueba");
        File ruta = new File("/home/alumnop/Carpetavacia");
        System.out.println(of.crearDirectorio(ruta, listaDirectorios));
         */
        
        
        //cambiar extensiones
        //System.out.println(of.cambiarExtensionFicheros("/home/alumnop/Carpetavacia/", "txt", "tiff"));
        
 /*FIBONACCI
        for (int i = 0; i < 10; i++) {
            System.out.print(of.fibonacci(i) + " ");
        }
         */
        //of.ListarFicheros("", true, true);
        //of.listarArchivosRecursivamente("/home/alumnop/Carpetavacia");
        //of.borrar("/home/alumnop/Carpetavacia/");
        

//Filtros
//Filtros filtro = new Filtros();
//System.out.println(of.listarFicheros(filtro.filtroGif()));
//of.listarFicherosConFiltroRecursivamente(filtro.filtroGif(), "/home/alumnop/Documentos");



//Cifrado
/*
try {
System.out.println(cifrar.cifrar("archivo.csv", "archivoCifrado.csv", 3, true));
} catch (IOException ex) {
Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
}
         */
        

//QUIJOTE
//try {
            //System.out.println(logicaQuijote.contarCantidadLetras("archivo.csv"));
            //System.out.println(logicaQuijote.contarLineas("archivo.csv"));
            //System.out.println(logicaQuijote.buscarPalabra("quijote.txt","quijote"));
            //System.out.println(logicaQuijote.escribirLineasAlReves("quijote.txt","quijotealreves.txt"));
  //          logicaQuijote.contarCantidadPalabrasDeUnFichero("quijote.txt");
           //System.out.println(logicaQuijote.separarEnCapitulos("quijote.txt"));
      //  } catch (IOException ex) {
    //       Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        //}
            
            System.out.println(of.listarDirectorioDeFormaRecursiva("/home/alumnop/Carpetavacia"));

    }

}
