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

    public File[] listarArchivosRecursivamente(String rutaQueSeQuiereListar) {
        File rutaQueSeQuiereListarHechaFile = new File(rutaQueSeQuiereListar);
        File[] ficherosDeLaRutaAListar = rutaQueSeQuiereListarHechaFile.listFiles();
        for (int contadorDelFicheroLeido = 0; contadorDelFicheroLeido < ficherosDeLaRutaAListar.length; contadorDelFicheroLeido++) {
            if (ficherosDeLaRutaAListar[contadorDelFicheroLeido].isDirectory() == false) {
                String guion = "-";
                for (int contadorDireferenciaEstructura = 0; contadorDireferenciaEstructura < ficherosDeLaRutaAListar[contadorDelFicheroLeido].getParent().length(); contadorDireferenciaEstructura++) {
                    guion += "-";
                }
                System.out.println("\n" + "./" + guion + "/" + ficherosDeLaRutaAListar[contadorDelFicheroLeido].getName());
            } else {
                
                System.out.println(ficherosDeLaRutaAListar[contadorDelFicheroLeido].getAbsolutePath());
                listarArchivosRecursivamente(ficherosDeLaRutaAListar[contadorDelFicheroLeido].getAbsolutePath());
            }
        }
        return ficherosDeLaRutaAListar;
    }

    public int eliminarDirectoriosVacios(String rutaParaListar) {
        File[] listaFicheros = listarArchivosRecursivamente(rutaParaListar);
        for (int contadorFicheros = 0; contadorFicheros < listaFicheros.length; contadorFicheros++) {
            System.out.println("Ruta: "+listaFicheros[contadorFicheros].getAbsolutePath());
            System.out.println("Nombre del directorio: " +listaFicheros[contadorFicheros]);
            System.out.println("Peso: " +listaFicheros[contadorFicheros].getTotalSpace());
        }
        return 1;
    }

    /*
    Sugiere  eliminar  directoriosvacíos.
    •Sugiere  eliminar  ficherosde  unas  determinadas  categorías.
    •Sugiere  eliminar  ficheros  de  gran  tamaño.
    •Sugiere  eliminar  ficheros  antiguos.
    •Busca  ficheros  duplicados
     */
}
