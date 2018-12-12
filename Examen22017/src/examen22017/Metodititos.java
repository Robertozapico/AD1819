/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen22017;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.diccionarioBinding.DiccionarioEspanol;
import jaxb.diccionarioBinding.PalabraType;
import jaxb.diccionarioBinding.TraduccionType;

/**
 *
 * @author alumnop
 */
public class Metodititos {

    private JAXBElement jaxbElement = null;
    private javax.xml.bind.JAXBContext jaxbCtx = null;
    private Marshaller m = null;

    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML) {
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(nombrePaqueteDeLasClasesGeneradasPorElXML);
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), DiccionarioEspanol.class); //NOI18N
            //Clientes clientes = (Clientes) new Clientes();
            //clientes = (Clientes) jaxbElement.getValue();
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return jaxbElement;
    }

    public int numTotalDefiniciones(DiccionarioEspanol diccionarioEspanol, String grafia) {
        for (PalabraType palabra : diccionarioEspanol.getPalabra()) {
            if (palabra.getGrafia().equals(grafia)) {
                return palabra.getDefinicion().size();
            }
        }
        return -1;
    }

    public Marshaller marshalizar(JAXBElement esquema) {
        try {
            m = jaxbCtx.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//PARA GRABAR EN UN FICHERO XML EN VEZ DE PONER SYSTEM.OUT SE PONE new File("algo.xml")
            //m.marshal(jaxbElement, System.out);
            m.marshal(jaxbElement, new File("algo.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(Metodititos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    //Borrartodas las traducciones de un determinado idioma (ej. PT)y lo grabe en otro fichero.
    public boolean borrarTraduccionesDeUnIdioma(DiccionarioEspanol diccionarioEspanol, String idioma) {
        for (PalabraType palabra : diccionarioEspanol.getPalabra()) {
            for (Iterator iterator = palabra.getTraducciones().getTraduccion().iterator(); iterator.hasNext();) {
                TraduccionType next = (TraduccionType) iterator.next();
                if (next.getIdiomaTraduccion().equals(idioma)) {
                    iterator.remove();
                }

            }
        }
        marshalizar(jaxbElement);
        return true;
    }

//    Generarun Map<K,V> donde K es el idioma de traducción y V es el total de traducciones que hay
    public Map generarColeccionMap(DiccionarioEspanol diccionarioEspanol) {
        Map<String, Integer> mapIdiomaTraduccion = new HashMap<String, Integer>();
        for (PalabraType palabra : diccionarioEspanol.getPalabra()) {
            for (Iterator iterator = palabra.getTraducciones().getTraduccion().iterator(); iterator.hasNext();) {
                TraduccionType next = (TraduccionType) iterator.next();
                next.getIdiomaTraduccion();
                //System.out.println(next.getIdiomaTraduccion());
                if (mapIdiomaTraduccion.containsKey(next.getIdiomaTraduccion())) {
                    mapIdiomaTraduccion.put(next.getIdiomaTraduccion(), mapIdiomaTraduccion.get(next.getIdiomaTraduccion()) + 1);
                } else {
                    mapIdiomaTraduccion.put(next.getIdiomaTraduccion(), 1);
                }
            }
        }
        return mapIdiomaTraduccion;
    }

    //Retornarpara una determinada palabra todos los sinónimos seguidos de sus definiciones
    public String cogerSinonimos(DiccionarioEspanol diccionarioEspanol, String palabraACoger) {
        String palabraDevuelta = palabraACoger + ": ";
        for (PalabraType palabra : diccionarioEspanol.getPalabra()) {
            if (palabra.getGrafia().equals(palabraACoger)) {
                for (String string : palabra.getDefinicion()) {
                    palabraDevuelta += "\n" + string;
                }

            }
        }
        return palabraDevuelta;
    }

    /*Crear un proyecto en Netbeans, que para el fichero json adjunto, implemente los métodos::
    a)Cuente el total de definiciones que contienen una determinada palabra.
    b)Genere un Map<K,V> donde K es el idioma de traducción  y V es el total de traducciones que hay.
    c)Genere una lista con la grafía de las  palabras añadidas hace menos de 3 días*/
    //si el json comienza como array esto tiene que devolver un jsonArray
    public static JsonObject leerJson(String rutaDocumentoJson) throws FileNotFoundException, IOException {
        JsonObject diccionarioEspanol = null;
        try {
            JsonReader jsonReader;

            InputStream fis = new FileInputStream(rutaDocumentoJson);
            jsonReader = Json.createReader(fis);
            //segun como empiece el json esto cambiara a readObject o a readArray
            diccionarioEspanol = jsonReader.readObject();

            jsonReader.close();

            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(Metodititos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return diccionarioEspanol;
    }
    //Cuente el total de definiciones que contienen una determinada palabra.
    public void totalDefiniciones(JsonObject objetoJson, String palabra){
        Map<String, Integer> mapIdiomaTraduccion = new HashMap<String, Integer>();
        JsonArray jsonArray = objetoJson.getJsonArray("palabra");
        
        
    }

}
