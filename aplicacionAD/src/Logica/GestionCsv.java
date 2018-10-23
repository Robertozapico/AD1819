/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author zapia
 */
public class GestionCsv {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/aa");

    private FileReader fr = null;
    private FileWriter ficheroGuardado = null;
    private BufferedReader registro = null;
    private BufferedWriter ficheroBufferGuardado = null;

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

    public void cerrarFicheroLectura() throws IOException {
        registro.close();
        fr.close();
    }
}
