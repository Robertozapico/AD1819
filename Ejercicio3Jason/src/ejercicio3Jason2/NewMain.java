/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3Jason2;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author alumnop
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        LogicaMetodos lm = new LogicaMetodos();
        File documentoXML = new File("clientes.xml");
        JAXBElement unMarshal = lm.unmarshalizar(documentoXML, "jaxb.clientesBinding");
        //lm.marshalizar(unMarshal);
        lm.volcarFicheroXMLAJson("", unMarshal);
    }

}
