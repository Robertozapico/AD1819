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
        //lm.borrarCliente(clientes, "Pablo", "String");
        
        
        lm.annadirCliente(clientes, "ACCESO", "DATOS", 978543212, "Jose", "Cervantes", 23, 2, "B", 33421, "Vegas");
        Clientes.Cliente clienteDireccion = clientes.getCliente().get(0);
        lm.annadirDireccionAUnCliente(clienteDireccion, "Callecita", 53, 2, "D", 33940, "El Entrego");
        lm.annadirDireccionAUnCliente(clienteDireccion, "Callecuela", 421, 3, "D", 33940, "El Entrego");
        lm.borrarDireccionSinCP(clientes);
        
        lm.marshalizar(unMarshal);
    }

}
