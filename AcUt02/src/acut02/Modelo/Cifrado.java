/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alumnop
 */
public class Cifrado {
//Ejercicio 3
    //FALTA GENERAR JAVADOC

    private FileInputStream FicheroALeer = null;
    private FileOutputStream FicheroAGrabar = null;
    private Map<String, Integer> distribucionLetras = new HashMap<>();
//Controlar solo para alfabeto español
//variables mas descriptivas

    /**
     *
     * @param ficheroOriginal
     * @param ficheroCodificado
     * @param codigo
     * @return caracteresCifrados
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int cifrar(String ficheroOriginal, String ficheroCodificado, int codigo, boolean descifrar) throws FileNotFoundException, IOException {
        int caracteresCifrados = 0;

        FicheroALeer = new FileInputStream(ficheroOriginal);
        FicheroAGrabar = new FileOutputStream(ficheroCodificado);
        int caracterLeido;
        if (descifrar == true) {
            while ((caracterLeido = FicheroALeer.read()) != -1) {
                int contadorLetras = 1;

                FicheroAGrabar.write(caracterLeido + codigo);
                caracteresCifrados++;
                if (distribucionLetras.containsKey(Character.toString((char) caracterLeido))) {
                    contadorLetras += distribucionLetras.get(Character.toString((char) caracterLeido));

                }
                distribucionLetras.put(Character.toString((char) caracterLeido), contadorLetras);
            }
            FicheroALeer.close();
            FicheroAGrabar.close();
        } else {
            while ((caracterLeido = FicheroALeer.read()) != -1) {
                int contadorLetras = 1;
                FicheroAGrabar.write(caracterLeido - codigo);
                caracteresCifrados++;
                if (distribucionLetras.containsKey(Character.toString((char) caracterLeido))) {
                    contadorLetras += distribucionLetras.get(Character.toString((char) caracterLeido));

                }
                distribucionLetras.put(Character.toString((char) caracterLeido), contadorLetras);
            }
            FicheroALeer.close();
            FicheroAGrabar.close();
        }
        System.out.println(distribucionLetras.toString());
        return caracteresCifrados;
    }

}
