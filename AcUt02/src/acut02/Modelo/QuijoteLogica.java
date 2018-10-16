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
    private FileOutputStream FicheroAGrabar = null;
    private Map<String, Long> distribucionLetras = new HashMap<>();

    public int contarCantidadLetras(String ficheroParaLeer) throws FileNotFoundException, IOException {

        FicheroALeer = new FileInputStream(ficheroParaLeer);
        int cantidadCaracteres = 0;
        int caracterLeido;

        while ((caracterLeido = FicheroALeer.read()) != -1) {
            long contadorLetras = 1;
            
            if(caracterLeido!=10&&caracterLeido!=32){
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
    
    public static int contarLineas(String ruta) throws FileNotFoundException, IOException{
        BufferedReader inputStream = new BufferedReader(new FileReader("quijote.txt"));
        int totalLineas=0;
        String linea;
        while((linea = inputStream.readLine()) !=null){
            totalLineas++;
        }
        return totalLineas;
    }
        
    public static int buscarPalabra(String ruta, String palabra) throws FileNotFoundException, IOException{
        BufferedReader inputStream = new BufferedReader(new FileReader("quijote.txt"));
        int totalVecesAparecePalabra=0;
        String linea;
        while((linea = inputStream.readLine()) !=null){
            if(linea.contains(palabra))
            totalVecesAparecePalabra++;
        }
        return totalVecesAparecePalabra;
    }
}
