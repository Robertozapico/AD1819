/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.LogicaMetodos;
import java.io.File;
import javax.xml.bind.JAXBElement;
import jaxb.clientes.Clientes;

/**
 *
 * @author alumnop
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LogicaMetodos lm = new LogicaMetodos();
        File documentoXML = new File("clientes.xml");
        JAXBElement unMarshal = lm.unmarshalizar(documentoXML, "jaxb.clientes");
        Clientes clientes = (Clientes) unMarshal.getValue();
        //System.out.println(lm.numTotalClientes(clientes));
        
        //System.out.println(lm.numTotalClientesDeUnaProvincia(clientes, 33456));
        lm.borrarCliente(clientes, "Gregorio", "String");
        //lm.marshalizar(unMarshal);
    }

}
