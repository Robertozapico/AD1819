/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author alumnop
 */
public class Metodos {

    /**
     * Crea el fichero json
     *
     * @param fichero
     * @throws IOException
     */
    public void crearFicheroJson(String fichero) throws IOException {
        //crea los libros
        JsonObject libro1 = Json.createObjectBuilder()
                .add("isbn", "isbn")
                .add("titulo", "nombrelibro")
                .add("autor", "nombreautor")
                .add("numeroPaginas", 300)
                .add("fechaPublicacion", "1700-07-15")
                .add("prestadoSiNo", "No")
                .add("fechaDevolucion", "2018-02-12")
                .build();//para cerrarlo:  }]
        //crea el array de libros
        JsonArray arrayJsonLibros = Json.createArrayBuilder()
                .add(libro1)
                .build();
        //crea la biblioteca
        JsonObject biblioteca = Json.createObjectBuilder()
                .add("libros", arrayJsonLibros)
                .build();

        FileWriter ficheroSalida = new FileWriter(fichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        //jsonWriter.writeArray(arrayJsonLibros);
        jsonWriter.writeObject(biblioteca);
        ficheroSalida.flush();
        ficheroSalida.close();
    }

    /**
     * Devuelve una lista de los socios y los libros prestados K es el codigo
     * del socio y V es la lista de los libros
     *
     * @return
     */
    public Map listaSociosConLibrosPrestados() throws FileNotFoundException {
        Map<String, List> mapListadoSociosConPrestamos = new HashMap<String, List>();
        FileReader entrada = new FileReader("fichero.json");
        JsonReader jsonReader = Json.createReader(entrada);
        JsonObject readedObject = jsonReader.readObject();
        JsonArray arrayJson = readedObject.getJsonArray("libros");
        for (JsonValue jsonValue : arrayJson) {
//           mapListadoSociosConPrestamos.put(key, arrayJson));
        }

        return mapListadoSociosConPrestamos;
    }

}
