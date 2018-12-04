/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3jason;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author alumnop
 */
public class Ejercicio3Jason {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos metodos = new Metodos();

        /*JsonObject cliente1 = Json.createObjectBuilder()
                .add("apellido", "Sanz")
                .add("apellido", "Sanchez")
                .add("direccion", Json.createArrayBuilder()//creamos el corchete
                        .add(Json.createObjectBuilder()
                                .add("calle", "callecita")
                                .add("numero", 53)
                                .add("piso", 2)
                                .add("escalera", "c")
                                .add("cp", 33940)
                                .add("ciudad", "El Entrego")
                        )//},  
                        .add(Json.createObjectBuilder()
                                .add("calle", "callina")
                                .add("numero", 13)
                                .add("piso", 6)
                                .add("escalera", "A")
                                .add("cp", 33940)
                                .add("ciudad", "El Entrego")
                        )//},  
                )//],
                .add("telefono", 342123242)
                .add("nombre", "Paco")
                .build();//para cerrarlo:  }]

        JsonObject cliente2 = Json.createObjectBuilder()
                .add("apellido", "Galvez")
                .add("apellido", "Amaral")
                .add("direccion", Json.createArrayBuilder()//creamos el corchete
                        .add(Json.createObjectBuilder()
                                .add("calle", "titanic")
                                .add("numero", 52)
                                .add("piso", 2)
                                .add("escalera", "c")
                                .add("cp", 33940)
                                .add("ciudad", "El Entrego")
                        )//},  
                        .add(Json.createObjectBuilder()
                                .add("calle", "callina")
                                .add("numero", 85)
                                .add("piso", 6)
                                .add("escalera", "A")
                                .add("cp", 33940)
                                .add("ciudad", "El Entrego")
                        )//},  
                )//],
                .add("telefono", 342123242)
                .add("nombre", "Juancar")
                .build();//para cerrarlo:  }]
        
        
        try {
            metodos.crearFicheroJSON("ficheroJSON.json", cliente1, cliente2);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio3Jason.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        //JsonObject cliente = metodos.crearClienteJson("Paco", "Pacoape", "callecita", 32, 2, 'C', 33940, "El Entrego", 89345345, "nombre");
        //System.out.println(metodos.crearDireccion("callecita", 32, 2, 'C', 33940, "El Entrego"));
        //System.out.println(cliente);
        JsonObjectBuilder direccion1 = metodos.crearDireccion("Madre", 42, 1, 'J', 44312, "La Felguera");
        JsonObjectBuilder direccion2 = metodos.crearDireccion("mia", 12, 54, 'Q', 42312, "Oviedo");
        List<JsonObjectBuilder> direcciones = new ArrayList<>();
        direcciones.add(direccion1);
        direcciones.add(direccion2);
        System.out.println(direccion1);
        System.out.println(direccion2);
        System.out.println(direcciones);
        JsonArrayBuilder arrayDeDirecciones = metodos.crearListadoDeDirecciones(direcciones);

        JsonObject cliente1 = metodos.crearClienteJsonConVariasDirecciones("Cecilio", "G", arrayDeDirecciones, 123534534, "Felipez");
        System.out.println(cliente1);
        try {
            metodos.crearFicheroJSON("ficheroJSON.json", cliente1);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio3Jason.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
