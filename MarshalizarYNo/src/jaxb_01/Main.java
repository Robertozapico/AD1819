/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01;

import java.io.File;
import javax.xml.bind.JAXBElement;
import jaxb.albaran.PedidoType;
import jaxb_01.logica.LogicaMetodos;

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
        File documentoXML = new File("albaran.xml");
        JAXBElement unMarshal = lm.unmarshalizar(documentoXML, "jaxb.albaran");
        PedidoType pedido = (PedidoType) unMarshal.getValue();
        lm.annadirProducto(pedido, "JJJJ", "SUSPENSO", 200, 20.0, "Vamos al pocete", 24, 3, 2003);
        lm.modificarDireccion(pedido, "PCComponentes", "Pruebecita", "Donosti", "Pais Vasco");
        System.out.println(lm.calcularImporte(pedido));
        lm.borrarProducto(pedido, "443-CA");
        
        lm.marshalizar(unMarshal);

    }

}
