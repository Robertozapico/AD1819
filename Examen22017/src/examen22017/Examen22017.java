/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen22017;

import java.io.File;
import java.io.IOException;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.xml.bind.JAXBElement;
import jaxb.diccionarioBinding.DiccionarioEspanol;

/**
 *
 * @author alumnop
 */
public class Examen22017 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Metodititos lm = new Metodititos();
        /*File documentoXML = new File("diccionario.xml");
        JAXBElement unMarshal = lm.unmarshalizar(documentoXML, "jaxb.diccionarioBinding");
        DiccionarioEspanol diccionario = (DiccionarioEspanol) unMarshal.getValue();    
        System.out.println(lm.numTotalDefiniciones(diccionario, "String3"));
        lm.borrarTraduccionesDeUnIdioma(diccionario, "Prueba");
        System.out.println(lm.generarColeccionMap(diccionario));
        System.out.println(lm.cogerSinonimos(diccionario, "String2"));
        */
        JsonObject jsonobjeto = lm.leerJson("diccionario.json");
        JsonArray jsonArray = jsonobjeto.getJsonArray("palabra");
        //lm.marshalizar(unMarshal);
    
    }
    
}
