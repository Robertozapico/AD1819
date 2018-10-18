/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;

/**
 *
 * @author zapia
 */
public class LogicaMetodos {

    /**
     *
     * @return
     */
    public File[] obtenerParticiones() {
        File[] particiones = File.listRoots();
        return particiones;
    }

    /**
     *
     * @param unidad
     * @return
     */
    public int espacioLibreDisponible(String unidad) {
        File unidadSeleccionada = new File(unidad);

        int espacioDisponibleDeLaUnidad = (int) (unidadSeleccionada.getFreeSpace() / 1073741824);
        return espacioDisponibleDeLaUnidad;
    }

    /**
     *
     * @param rutaEscaneada
     * @return
     */
    public File[] escanearUnidad(String rutaEscaneada) {
        File unidadSeleccionada = new File(rutaEscaneada);
        File[] ficherosDeLaUnidad = unidadSeleccionada.listFiles();
        int ficheroCogido;
        if (ficherosDeLaUnidad != null) {
            for (ficheroCogido = 0; ficheroCogido < ficherosDeLaUnidad.length; ficheroCogido++) {
                if (ficherosDeLaUnidad[ficheroCogido].isDirectory()) {

                    escanearUnidad(ficherosDeLaUnidad[ficheroCogido].getAbsolutePath());
                }
                System.out.println(ficherosDeLaUnidad[ficheroCogido].getName());
            }
        }
        System.out.println("-------------------------------------------");
        return ficherosDeLaUnidad;
    }

    /*
    Sugiere  eliminar  directoriosvacíos.
    •Sugiere  eliminar  ficherosde  unas  determinadas  categorías.
    •Sugiere  eliminar  ficheros  de  gran  tamaño.
    •Sugiere  eliminar  ficheros  antiguos.
    •Busca  ficheros  duplicados
     */
}
