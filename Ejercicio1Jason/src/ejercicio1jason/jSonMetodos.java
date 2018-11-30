/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1jason;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author alumnop
 */
public class jSonMetodos {

    public boolean crearFicheros(String fichero) throws IOException {
        JsonObject libro1 = Json.createObjectBuilder()
                .add("titulo", "Sueños IA")
                .add("totalPaginas", 210)
                .add("precio", 10)
                .add("autores", Json.createArrayBuilder()//creamos el corchete
                        .add(Json.createObjectBuilder()
                                .add("nombre", "Javier")
                                .add("apellido", "Perez")
                        )//},  
                        .add(Json.createObjectBuilder()
                                .add("nombre", "María")
                                .add("apellido", "Rodriguez")
                        )//}, 
                )//],
                .add("generos", Json.createArrayBuilder()//generos:[
                        .add(Json.createObjectBuilder()//creamos{
                                .add("generos", "novela")
                        )//},
                        .add(Json.createObjectBuilder()
                                .add("genero", "ficcion")
                        )//},

                )//]
                .build();//para cerrarlo:  }]
        JsonObject libro2 = Json.createObjectBuilder()
                .add("titulo", "JSON para todos")
                .add("totalPaginas", 310)
                .add("precio", 20)
                .add("autores", Json.createArrayBuilder()//creamos el corchete
                        .add(Json.createObjectBuilder()
                                .add("nombre", "Ana")
                                .add("apellido", "Cota")
                        )//},  
                )//],
                .add("generos", Json.createArrayBuilder()//generos:[
                        .add(Json.createObjectBuilder()//creamos{
                                .add("generos", "informática")
                        )//},
                        .add(Json.createObjectBuilder()
                                .add("genero", "JSON")
                        )//},

                )//]
                .build();//para cerrarlo:  }]

        JsonArray arrayJsonLibros = Json.createArrayBuilder().add(libro1)
                .add(libro2)
                .build();
        FileWriter ficheroSalida = new FileWriter(fichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJsonLibros);
        ficheroSalida.flush();
        ficheroSalida.close();
        return true;
    }

    public int cantidadDeLibros() throws FileNotFoundException {

        // Ejemplo entrada
        FileReader entrada = new FileReader("ficheroJSON.json");
        JsonReader jsonReader = Json.createReader(entrada);
        JsonArray readedArray = jsonReader.readArray();
        return readedArray.size();
    }

    public JsonArray informacionJson() throws FileNotFoundException {
        JsonArray readedArray = null;
        // Ejemplo entrada
        FileReader entrada = new FileReader("ficheroJSON.json");
        JsonReader jsonReader = Json.createReader(entrada);
        readedArray = jsonReader.readArray();
        return readedArray;
    }

    public List titulosDeLibros(JsonArray arrayLibros) {
        List<String> titulos = new ArrayList<String>();
        for (int i = 0; i < arrayLibros.size(); i++) {
            //System.out.println(arrayLibros);
            //System.out.println(arrayLibros.getJsonObject(i).getString("titulo"));
            //System.out.println(arrayLibros.getJsonObject(i).getJsonNumber("precio"));
            titulos.add(arrayLibros.getJsonObject(i).getString("titulo"));
        }
        return titulos;
    }

    public boolean autorLibro(int libroEscogido, int autorEscogido, JsonArray arrayLibros) {
        libroEscogido -= 1;
        autorEscogido -= 1;
        if (arrayLibros.size() > libroEscogido && arrayLibros.getJsonObject(libroEscogido).getJsonArray("autores").size() > autorEscogido) {
            System.out.println(arrayLibros.getJsonObject(libroEscogido).getJsonArray("autores").getJsonObject(autorEscogido).getJsonString("nombre"));
            return true;
        } else {
            System.out.println("No existe ese libro o ese autor");
            return false;
        }
    }

    public int valorLibrosEnStock(JsonArray arrayLibros) {
        int dinero = 0;
        for (int i = 0; i < arrayLibros.size(); i++) {
            JsonNumber precio = arrayLibros.getJsonObject(i).getJsonNumber("precio");
            dinero += precio.intValue();
        }
        return dinero;
    }

    
    
}
