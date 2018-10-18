/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alumnop
 */
public class QuijoteLogica {

    private FileInputStream FicheroALeer = null;
    private Map<String, Long> distribucionLetrasDeUnFichero = new HashMap<>();
    private Map<String, Float> palabrasDeUnFichero = new HashMap<>();

    /**
     *
     * @param ficheroParaLeer
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int contarCantidadLetrasDeUnFichero(String ficheroParaLeer) throws FileNotFoundException, IOException {

        FicheroALeer = new FileInputStream(ficheroParaLeer);
        int cantidadCaracteresDeUnFichero = 0;
        int caracterLeidoDelFichero;

        while ((caracterLeidoDelFichero = FicheroALeer.read()) != -1) {
            long contadorCuantasVecesApareceUnaLetra = 1;

            if (caracterLeidoDelFichero != 10 && caracterLeidoDelFichero != 32) {
                cantidadCaracteresDeUnFichero++;
                if (distribucionLetrasDeUnFichero.containsKey(Character.toString((char) caracterLeidoDelFichero))) {
                    contadorCuantasVecesApareceUnaLetra += distribucionLetrasDeUnFichero.get(Character.toString((char) caracterLeidoDelFichero));

                }
                distribucionLetrasDeUnFichero.put(Character.toString((char) caracterLeidoDelFichero), contadorCuantasVecesApareceUnaLetra);
            }
        }
        FicheroALeer.close();

        System.out.println(distribucionLetrasDeUnFichero.toString());
        return cantidadCaracteresDeUnFichero;
    }

    /**
     *
     * @param rutaDelFicheroParaLeer
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static int contarLineasDeUnFichero(String rutaDelFicheroParaLeer) throws FileNotFoundException, IOException {
        BufferedReader ficheroLeido = new BufferedReader(new FileReader("quijote.txt"));
        int totalLineasLeidasDelFichero = 0;
        String lineaLeidaDelFicheroLeido;
        while ((lineaLeidaDelFicheroLeido = ficheroLeido.readLine()) != null) {
            totalLineasLeidasDelFichero++;
        }
        return totalLineasLeidasDelFichero;
    }

    /**
     *
     * @param rutaDelFicheroParaLeer
     * @param ficheroNuevoDondeGuardarLaInformacion
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static int buscarPalabraEnUnFichero(String rutaDelFicheroParaLeer, String ficheroNuevoDondeGuardarLaInformacion) throws FileNotFoundException, IOException {
        BufferedReader ficheroLeido = new BufferedReader(new FileReader("quijote.txt"));
        int totalVecesApareceLaPalabra = 0;
        String lineaLeidaDelFicheroLeido;
        while ((lineaLeidaDelFicheroLeido = ficheroLeido.readLine()) != null) {
            lineaLeidaDelFicheroLeido = lineaLeidaDelFicheroLeido.toLowerCase();
            if (lineaLeidaDelFicheroLeido.contains(ficheroNuevoDondeGuardarLaInformacion)) {
                totalVecesApareceLaPalabra++;
            }
        }
        return totalVecesApareceLaPalabra;
    }

    /**
     *
     * @param rutaDelFicheroParaLeer
     * @param ficheroNuevoDondeGuardarLaInformacion
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static int escribirLineasAlRevesDeUnFichero(String rutaDelFicheroParaLeer, String ficheroNuevoDondeGuardarLaInformacion) throws FileNotFoundException, IOException {
        BufferedReader ficheroLeido = new BufferedReader(new FileReader(rutaDelFicheroParaLeer));
        FileWriter ficheroParaGrabar = new FileWriter(ficheroNuevoDondeGuardarLaInformacion, true);
        int palabrasDadasDeVuelta = 0;
        String lineaLeidaDelFicheroLeido;

        while ((lineaLeidaDelFicheroLeido = ficheroLeido.readLine()) != null) {
            StringBuilder stringParaDarLaVueltaAUnString = new StringBuilder(lineaLeidaDelFicheroLeido);
            palabrasDadasDeVuelta++;
            String lineaDadaVuelta = stringParaDarLaVueltaAUnString.reverse().toString();
            ficheroParaGrabar.write(lineaDadaVuelta + "\n");
        }
        return palabrasDadasDeVuelta;
    }

    /**
     *
     * @param rutaDelFicheroParaLeer
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int contarCantidadPalabrasDeUnFichero(String rutaDelFicheroParaLeer) throws FileNotFoundException, IOException {

        BufferedReader ficheroLeido = new BufferedReader(new FileReader(rutaDelFicheroParaLeer));

        float cantidadVecesApareceLaPalabra = 0;
        int CaracterLeidoDelFichero;
        String palabraLeidaResultanteDelCaracterLeido = "";

        while ((CaracterLeidoDelFichero = ficheroLeido.read()) != -1) {
            cantidadVecesApareceLaPalabra = 1;

            if (CaracterLeidoDelFichero != 10 && CaracterLeidoDelFichero != 32) {
                palabraLeidaResultanteDelCaracterLeido += (char) CaracterLeidoDelFichero;
            } else {
                if (palabrasDeUnFichero.containsKey(palabraLeidaResultanteDelCaracterLeido)) {
                    cantidadVecesApareceLaPalabra += palabrasDeUnFichero.get(palabraLeidaResultanteDelCaracterLeido);
                }
                if (CaracterLeidoDelFichero != 10 || CaracterLeidoDelFichero != 32) {
                    palabrasDeUnFichero.put(palabraLeidaResultanteDelCaracterLeido, cantidadVecesApareceLaPalabra);
                    palabraLeidaResultanteDelCaracterLeido = "";
                }
            }

        }
        if (palabrasDeUnFichero.containsKey(palabraLeidaResultanteDelCaracterLeido)) {
            cantidadVecesApareceLaPalabra += palabrasDeUnFichero.get(palabraLeidaResultanteDelCaracterLeido);
        }
        palabrasDeUnFichero.put(palabraLeidaResultanteDelCaracterLeido, cantidadVecesApareceLaPalabra);
        System.out.println(palabrasDeUnFichero.toString());
        ficheroLeido.close();

        return palabrasDeUnFichero.size();
    }

    /**
     *
     * @param rutaDelFicheroParaLeer
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int separarEnCapitulosDeUnFichero(String rutaDelFicheroParaLeer) throws FileNotFoundException, IOException {

        BufferedReader ficheroParaLeer = new BufferedReader(new FileReader(rutaDelFicheroParaLeer));
        FileWriter ficheroParaGrabar;
        int cantidadLineasLeidas = 0;
        String lineaLeida;

        int contadorFicherosCreados = 0;
        while ((lineaLeida = ficheroParaLeer.readLine()) != null) {
            if (!lineaLeida.contains("Capítulo")) {
                ficheroParaGrabar = new FileWriter("ficheronuevo" + contadorFicherosCreados + ".txt", true);
                ficheroParaGrabar.write(lineaLeida + "\n");
                ficheroParaGrabar.close();
                cantidadLineasLeidas++;
            } else {
                contadorFicherosCreados++;
                ficheroParaGrabar = new FileWriter("ficheronuevo" + contadorFicherosCreados + ".txt", true);
                ficheroParaGrabar.write(lineaLeida + "\n");
                ficheroParaGrabar.close();
                cantidadLineasLeidas++;
            }

        }

        return cantidadLineasLeidas;
    }

}
