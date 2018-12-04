/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3jason;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author alumnop
 */
public class Metodos {
    //Crea  un  fichero  JSON  (jsonWriter)  con  la  estructura  del  XSD  que  añade  un  cliente  con  dos  direcciones.
/*
    private JAXBElement jaxbElement = null;
    private javax.xml.bind.JAXBContext jaxbCtx = null;
    private Marshaller m = null;

    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML) {
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(nombrePaqueteDeLasClasesGeneradasPorElXML);
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), Clientes.class); //NOI18N
            //Clientes clientes = (Clientes) new Clientes();
            //clientes = (Clientes) jaxbElement.getValue();
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return jaxbElement;
    }*/
    public JsonObject crearClienteJson(String apellido1, String apellido2, String calle, int numero, int piso, char escalera, int cp, String ciudad, int telefono, String nombre) {
        JsonObject cliente = Json.createObjectBuilder()
                .add("apellidos", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("apellido", apellido1)
                                .add("apellido", apellido2)
                        )
                )
                .add("direccion", Json.createArrayBuilder()//creamos el corchete
                        .add(Json.createObjectBuilder()
                                .add("calle", calle)
                                .add("numero", numero)
                                .add("piso", piso)
                                .add("escalera", escalera)
                                .add("cp", cp)
                                .add("ciudad", ciudad)
                        )//}, 
                )//],
                .add("telefono", telefono)
                .add("nombre", nombre)
                .build();//para cerrarlo:  }]
        return cliente;
    }

    public JsonObject crearClienteJsonConVariasDirecciones(String apellido1, String apellido2, JsonArrayBuilder listadoDeDirecciones, int telefono, String nombre) {
        JsonObject cliente = Json.createObjectBuilder()
                .add("apellidos", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("apellido1", apellido1)
                                .add("apellido2", apellido2)
                        )
                )
                .add("direccion", listadoDeDirecciones//creamos el corchete//}, 
                )//],
                .add("telefono", telefono)
                .add("nombre", nombre)
                .build();//para cerrarlo:  }]
        return cliente;
    }

    public JsonObjectBuilder crearDireccion(String calle, int numero, int piso, char escalera, int cp, String ciudad) {
        JsonObjectBuilder direcciones = Json.createObjectBuilder()
                .add("calle", calle)
                .add("numero", numero)
                .add("piso", piso)
                .add("escalera", escalera)
                .add("cp", cp)
                .add("ciudad", ciudad);

        return direcciones;
    }

    public JsonArrayBuilder crearListadoDeDirecciones(List<JsonObjectBuilder> listadoDeDirecciones) {
        JsonArrayBuilder direcciones = Json.createArrayBuilder();
        for (JsonObjectBuilder listadoDeDireccione : listadoDeDirecciones) {
            direcciones.add(listadoDeDireccione);
        }

        return direcciones;
    }

    public void crearFicheroJSON(String rutaFichero, JsonObject cliente1) throws IOException {

        JsonArray arrayJson = Json.createArrayBuilder().add(cliente1)
                .build();
        FileWriter ficheroSalida = new FileWriter(rutaFichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();

    }

    public void contarTotalClientes() {

    }

}
