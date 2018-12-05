/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3Jason2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientesBinding.Clientes;
import jaxb.clientesBinding.TipoDireccion;

/**
 *
 * @author alumnop
 */
public class LogicaMetodos {

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
    }

    public Marshaller marshalizar(JAXBElement esquema) {
        try {
            m = jaxbCtx.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(jaxbElement, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public void crearFicheroJSON(String rutaFichero, JsonArrayBuilder clientes) throws IOException {

        JsonArray arrayJson = clientes
                .build();
        FileWriter ficheroSalida = new FileWriter(rutaFichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();

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

    public JsonArrayBuilder crearListadoDeDirecciones(List<TipoDireccion> listadoDeDirecciones) {
        JsonArrayBuilder direcciones = Json.createArrayBuilder();
        List<JsonObjectBuilder> listaDeDireccionJson = new ArrayList<>();
        for (TipoDireccion listadoDeDireccione : listadoDeDirecciones) {
            
            //JsonObjectBuilder direccionJsonObject = (JsonObjectBuilder) listadoDeDireccione;
            direcciones.add(crearDireccion(listadoDeDireccione.getCalle(), 0, 0, 0, 0, ciudad));
        }
        
        return direcciones;
    }

    public JsonArrayBuilder crearListadoDeClientes(List<JsonObject> listadoDeClientes) {
        JsonArrayBuilder clientes = Json.createArrayBuilder();
        for (JsonObject listadoDeDireccione : listadoDeClientes) {
            clientes.add(listadoDeDireccione);
        }

        return clientes;
    }

    public void volcarFicheroXMLAJson(String rutaFichero, JAXBElement esquema) throws IOException {
        Clientes clientes = (Clientes) esquema.getValue();
        List<JsonObject> clientesJsonObject = new ArrayList<>();
        for (Clientes.Cliente cliente : clientes.getCliente()) {
            JsonArrayBuilder listadoDeDirecciones = crearListadoDeDirecciones(cliente.getDireccion());

            JsonObject clienteJson = crearClienteJsonConVariasDirecciones(cliente.getApellido().get(0), cliente.getApellido().get(1), listadoDeDirecciones, Integer.parseInt(cliente.getTelefono()), cliente.getNombre());
            clientesJsonObject.add(clienteJson);
            /*cliente clientesJsonObject
            .add(cliente);
            JsonArrayBuilder clientesArray = metodos.crearListadoDeClientes(clientes);
            System.out.println(cliente);*/
        }
        JsonArrayBuilder clientesArray = crearListadoDeClientes(clientesJsonObject);
        crearFicheroJSON("ficheroJSON.json", clientesArray);
    }
}
