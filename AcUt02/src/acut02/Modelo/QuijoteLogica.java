/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author alumnop
 */
public class QuijoteLogica {

    private FileInputStream FicheroALeer = null;
    private Map<String, Long> distribucionLetras = new HashMap<>();
    private Map<String, Long> distribucionPalabras = new HashMap<>();

    // e.Mostrar  las  palabras  distintas  que  hay  en  el  texto  y  el  número  de  veces  que  aparecen.  f.Dividir  el  fichero  de  El  Quijote  en  los  distintos  capítulos. 
    public int contarCantidadLetras(String ficheroParaLeer) throws FileNotFoundException, IOException {

        FicheroALeer = new FileInputStream(ficheroParaLeer);
        int cantidadCaracteres = 0;
        int caracterLeido;

        while ((caracterLeido = FicheroALeer.read()) != -1) {
            long contadorLetras = 1;

            if (caracterLeido != 10 && caracterLeido != 32) {
                cantidadCaracteres++;
                if (distribucionLetras.containsKey(Character.toString((char) caracterLeido))) {
                    contadorLetras += distribucionLetras.get(Character.toString((char) caracterLeido));

                }
                distribucionLetras.put(Character.toString((char) caracterLeido), contadorLetras);
            }
        }
        FicheroALeer.close();

        System.out.println(distribucionLetras.toString());
        return cantidadCaracteres;
    }

    public static int contarLineas(String ruta) throws FileNotFoundException, IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader("quijote.txt"));
        int totalLineas = 0;
        String linea;
        while ((linea = inputStream.readLine()) != null) {
            totalLineas++;
        }
        return totalLineas;
    }

    public static int buscarPalabra(String ruta, String palabra) throws FileNotFoundException, IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader("quijote.txt"));
        int totalVecesAparecePalabra = 0;
        String linea;
        while ((linea = inputStream.readLine()) != null) {
            linea = linea.toLowerCase();
            if (linea.contains(palabra)) {
                totalVecesAparecePalabra++;
            }
        }
        return totalVecesAparecePalabra;
    }

    public static int escribirLineasAlReves(String ruta, String ficheronuevo) throws FileNotFoundException, IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader(ruta));
        FileWriter outputStream = new FileWriter(ficheronuevo, true);
        int palabrasDadasDeVuelta = 0;
        String linea;

        while ((linea = inputStream.readLine()) != null) {
            StringBuilder reverse = new StringBuilder(linea);
            palabrasDadasDeVuelta++;
            String lineaDadaVuelta = reverse.reverse().toString();
            //System.out.println(reverse.reverse().toString());
            outputStream.write(lineaDadaVuelta + "\n");
        }
        return palabrasDadasDeVuelta;
    }

    public int contarCantidadPalabras(String ruta) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(ruta));

        int cantidadPalabras = 0;
        int caracterLeido;
        String palabraLeida = "";
        while ((caracterLeido = inputStream.read()) != -1) {
            long contadorLetras = 1;

            if (caracterLeido != 10 && caracterLeido != 32) {
                palabraLeida += (char) caracterLeido;
                if (distribucionLetras.containsKey(palabraLeida)) {
                    contadorLetras += distribucionLetras.get(palabraLeida);

                }
                distribucionLetras.put(palabraLeida, contadorLetras);
                cantidadPalabras++;
            } else {
                palabraLeida = "";
            }

        }
        System.out.println(distribucionLetras.toString());
        inputStream.close();

        return cantidadPalabras;
    }

    public int separarEnCapitulos(String ruta) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(ruta));
        FileWriter outputStream;
        int cantidadLineas = 0;
        String linea;
        
        int contadorFicheros = 0;
        while ((linea = inputStream.readLine()) != null) {
            if (!linea.contains("Capítulo")) {
                outputStream = new FileWriter("ficheronuevo" + contadorFicheros+".txt", true);
                outputStream.write(linea+"\n");
                outputStream.close();
                cantidadLineas++;
            } else {
                contadorFicheros++;
                outputStream = new FileWriter("ficheronuevo" + contadorFicheros+".txt", true);
                outputStream.write(linea+"\n");
                outputStream.close();
                cantidadLineas++;
            }

        }

        return cantidadLineas;
    }

}
