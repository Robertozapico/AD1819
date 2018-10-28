/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author zapia
 */
public class GestionCsv {

    private FileWriter ficheroGuardado = null;
    private BufferedWriter ficheroBufferGuardado = null;


/**
 * Metodo para guardar el nombre de un fichero y su ruta en un fichero csv.
 * @param nombreDelFichero nombre que se le quiere dar al fichero csv donde se va a guardar la información
 * @param listaDeFicheros la lista de ficheros de la que se va a guardar su información
 * @return el número de ficheros que han sido grabados
 * @throws ParseException
 * @throws IOException 
 */
    public int grabarFicheroCSV(String nombreDelFichero, File[] listaDeFicheros) throws ParseException, IOException {
        ficheroGuardado = new FileWriter(nombreDelFichero);
        ficheroBufferGuardado = new BufferedWriter(ficheroGuardado);
        int numeroFicherosGrabados = 0;
        for (int contadorDeFicheros = 0; contadorDeFicheros < listaDeFicheros.length; contadorDeFicheros++) {
            ficheroBufferGuardado.write(listaDeFicheros[contadorDeFicheros].getName() + ",");
            ficheroBufferGuardado.write(listaDeFicheros[contadorDeFicheros].getAbsolutePath() + "\n");
            numeroFicherosGrabados++;
        }
        ficheroBufferGuardado.close();
        ficheroGuardado.close();

        return numeroFicherosGrabados;
    }

}
