/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zapia
 */
public class LogicaMetodos {

    private List<File> coleccionFicheros = new ArrayList<File>();
    private GestionCsv gestionDeFicheroCsv = new GestionCsv();

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
                if (!coleccionFicheros.contains(ficherosDeLaUnidad[ficheroCogido])) {
                    if (ficherosDeLaUnidad[ficheroCogido].isDirectory()) {
                        coleccionFicheros.add(ficherosDeLaUnidad[ficheroCogido]);
                        escanearUnidad(ficherosDeLaUnidad[ficheroCogido].getAbsolutePath());
                    } else {
                        coleccionFicheros.add(ficherosDeLaUnidad[ficheroCogido]);
                    }
                }
            }
        }
        ficherosDeLaUnidad = coleccionFicheros.toArray(new File[coleccionFicheros.size()]);
        return ficherosDeLaUnidad;
    }

    public File[] listarArchivosRecursivamente(String rutaQueSeQuiereListar) {
        File rutaQueSeQuiereListarHechaFile = new File(rutaQueSeQuiereListar);
        File[] ficherosDeLaRutaAListar = rutaQueSeQuiereListarHechaFile.listFiles();
        if (ficherosDeLaRutaAListar != null) {
            for (int contadorDelFicheroLeido = 0; contadorDelFicheroLeido < ficherosDeLaRutaAListar.length; contadorDelFicheroLeido++) {
                if (ficherosDeLaRutaAListar[contadorDelFicheroLeido].isDirectory() == false) {
                    coleccionFicheros.add(ficherosDeLaRutaAListar[contadorDelFicheroLeido]);
                } else {
                    //coleccionFicheros.add(ficherosDeLaRutaAListar[contadorDelFicheroLeido]);
                    //System.out.println(ficherosDeLaRutaAListar[contadorDelFicheroLeido].getAbsolutePath());
                    listarArchivosRecursivamente(ficherosDeLaRutaAListar[contadorDelFicheroLeido].getAbsolutePath());
                }
            }
        }
        ficherosDeLaRutaAListar = coleccionFicheros.toArray(new File[coleccionFicheros.size()]);
        return ficherosDeLaRutaAListar;
    }
//en proceso

    public int eliminarDirectoriosVacios(String rutaParaListar) {
        File[] listaFicheros = listarArchivosRecursivamente(rutaParaListar);
        for (int contadorFicheros = 0; contadorFicheros < listaFicheros.length; contadorFicheros++) {
            System.out.println("Ruta: " + listaFicheros[contadorFicheros].getAbsolutePath());
            System.out.println("Nombre del directorio: " + listaFicheros[contadorFicheros]);
            System.out.println("Peso: " + listaFicheros[contadorFicheros].getTotalSpace());
        }
        return 1;
    }

    public File[] escanearFicherosPorTamanio(int opcion, String rutaEscaneada) {
        List<File> coleccionFicherosParaBorrar = new ArrayList<File>();
        File[] ficherosEscaneados;
        File[] ficherosDeLaUnidad = escanearUnidad(rutaEscaneada);
        double pesoMaximo = 0;
        double pesoMinimo = 0;
        double pesoFichero = 0;
        if (opcion == 0) {
            pesoMaximo = 700;
            pesoMinimo = 0;
        } else if (opcion == 1) {
            //poner D detras del 1024
            pesoMaximo = (10 * 1024);
            pesoMinimo = 700;
        }
        for (File fichero : ficherosDeLaUnidad) {
            pesoFichero = (fichero.length() / 1024) / 1024;
            if (pesoFichero <= pesoMaximo && pesoFichero >= pesoMinimo) {
                coleccionFicherosParaBorrar.add(fichero);
            } else if (opcion == 3) {
                coleccionFicherosParaBorrar.add(fichero);
            }
        }
        ficherosEscaneados = coleccionFicherosParaBorrar.toArray(new File[coleccionFicherosParaBorrar.size()]);
        return ficherosEscaneados;
    }

    public int borradoDeFicheros(File[] ficherosEscogidos, String nombreFichero) {
        int contadorFicherosBorrados = 0;
        try {
            gestionDeFicheroCsv.grabarFicheroCSV(nombreFichero, ficherosEscogidos);
        } catch (ParseException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (File fichero : ficherosEscogidos) {
            fichero.delete();
            contadorFicherosBorrados++;
        }
        return contadorFicherosBorrados;
    }

    public ArrayList<File> listarFicherosConFiltroRecursivamente(int tipo, String rutaQueSeQuiereListar) {
        Filtros filtro = new Filtros();
        FilenameFilter filtroEscogido = null;
        if (tipo == 0) {
            filtroEscogido = filtro.filtroImagenes();
        } else if (tipo == 1) {
            filtroEscogido = filtro.filtroVideo();
        } else if (tipo == 2) {
            filtroEscogido = filtro.filtroAudio();
        } else if (tipo == 3) {
            filtroEscogido = filtro.filtroTexto();
        }
        File[] listaFicherosFiltrados = listarArchivosRecursivamente(rutaQueSeQuiereListar);
        ArrayList<File> listaFicherosConFiltro = new ArrayList<File>();
        for (int contadorFicheros = 0; contadorFicheros < listaFicherosFiltrados.length; contadorFicheros++) {
            if (filtroEscogido.accept(listaFicherosFiltrados[contadorFicheros], listaFicherosFiltrados[contadorFicheros].getName())) {
                if (!listaFicherosConFiltro.contains(listaFicherosFiltrados[contadorFicheros])) {
                    listaFicherosConFiltro.add(listaFicherosFiltrados[contadorFicheros]);
                }
            }
        }
        return listaFicherosConFiltro;
    }

    public ArrayList<File> listarFicherosPorFechaRecursivamente(int tipo, String rutaQueSeQuiereListar) {
        Filtros filtro = new Filtros();
        FilenameFilter filtroEscogido = null;
        if (tipo == 0) {
            filtroEscogido = filtro.filtroPorFechaDeModificacion(2184L);
        } else if (tipo == 1) {
            filtroEscogido = filtro.filtroPorFechaDeModificacion(4368L);
        } else if (tipo == 2) {
            filtroEscogido = filtro.filtroPorFechaDeModificacion(8784L);
        }
        File[] listaFicherosFiltrados = listarArchivosRecursivamente(rutaQueSeQuiereListar);
        ArrayList<File> listaFicherosConFiltro = new ArrayList<File>();
        for (int contadorFicheros = 0; contadorFicheros < listaFicherosFiltrados.length; contadorFicheros++) {
            if (filtroEscogido.accept(listaFicherosFiltrados[contadorFicheros], listaFicherosFiltrados[contadorFicheros].getName())) {
                if (!listaFicherosConFiltro.contains(listaFicherosFiltrados[contadorFicheros])) {
                    listaFicherosConFiltro.add(listaFicherosFiltrados[contadorFicheros]);
                }
            }
        }
        return listaFicherosConFiltro;
    }

    /*
    Sugiere  eliminar  directoriosvacíos.
    •Busca  ficheros  duplicados
     */
}
