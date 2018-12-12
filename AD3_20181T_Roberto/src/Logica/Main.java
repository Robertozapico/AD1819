/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.LogicaMetodos;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBElement;
import jaxb.bibliotecaBinding.Biblioteca;
import jaxb.bibliotecaBinding.PrestamoType;

/**
 *
 * @author alumnop
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        LogicaMetodos lm = new LogicaMetodos();
        File documentoXML = new File("BIBLIOTECA.xml");
        JAXBElement unMarshal = lm.unmarshalizar(documentoXML, "jaxb.bibliotecaBinding");
        Biblioteca biblioteca = (Biblioteca) unMarshal.getValue();
        PrestamoType prestamo = lm.crearPrestamo(biblioteca, "isbnPropio", "tituloExamen", "213123", "Roberto", "Zapico", 5, 6, 2007);
        System.out.println(lm.insertarPrestamo(biblioteca, prestamo));
        lm.darDeBajaAUnSocio(biblioteca, "0000");
        System.out.println(lm.obtenerListadoDeSociosYSusPrestamos(biblioteca));
        System.out.println(lm.obtenerListadoLibrosNoDevueltosATiempo(biblioteca,"0000", 18, 9, 2018));
        lm.marshalizar(unMarshal);
        
        lm.crearFicheroJSONDeSocios("socios.json", biblioteca);
    }

}
