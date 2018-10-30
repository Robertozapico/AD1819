/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaz.PantallaEscaneo;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author zapia
 */
public class LogicaMetodos {

    private List<File> coleccionFicheros = new ArrayList<File>();
    private GestionCsv gestionDeFicheroCsv = new GestionCsv();

    /**
     * Obtiene las particiones de un sistema
     *
     * @return
     */
    public File[] obtenerParticiones() {
        File[] particiones = File.listRoots();
        return particiones;
    }

    /**
     * Obtiene el espacio libre de una unidad.
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
     * Lista los ficheros de una unidad con recursividad
     *
     * @param rutaQueSeQuiereListar
     * @return
     */
    public File[] listarArchivosRecursivamente(String rutaQueSeQuiereListar) {
        File rutaQueSeQuiereListarHechaFile = new File(rutaQueSeQuiereListar);
        File[] ficherosDeLaRutaAListar = rutaQueSeQuiereListarHechaFile.listFiles();
        if (ficherosDeLaRutaAListar != null) {
            for (int contadorDelFicheroLeido = 0; contadorDelFicheroLeido < ficherosDeLaRutaAListar.length; contadorDelFicheroLeido++) {
                if (!coleccionFicheros.contains(ficherosDeLaRutaAListar[contadorDelFicheroLeido])) {
                    if (ficherosDeLaRutaAListar[contadorDelFicheroLeido].isDirectory() == false) {
                        coleccionFicheros.add(ficherosDeLaRutaAListar[contadorDelFicheroLeido]);
                    } else {
                        coleccionFicheros.add(ficherosDeLaRutaAListar[contadorDelFicheroLeido]);
                        listarArchivosRecursivamente(ficherosDeLaRutaAListar[contadorDelFicheroLeido].getAbsolutePath());
                    }
                }
            }
        }
        ficherosDeLaRutaAListar = coleccionFicheros.toArray(new File[coleccionFicheros.size()]);
        return ficherosDeLaRutaAListar;
    }

    /**
     * Escanea los ficheros de una unidad y los devuelve dependiendo del peso
     * mínimo y máximo.
     *
     * @param opcion para escoger un peso máximo y una mínimo
     * @param rutaEscaneada de los ficheros que se quieren listar
     * @return los ficheros en base del tamaño escogido
     */
    public File[] escanearFicherosPorTamanio(int opcion, String rutaEscaneada) {
        List<File> coleccionFicherosParaBorrar = new ArrayList<File>();
        File[] ficherosEscaneados;
        File[] ficherosDeLaUnidad = listarArchivosRecursivamente(rutaEscaneada);
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

    /**
     * Borra ficheros y guarda los ficheros que han sido borrados en un archivo
     * csv como registro
     *
     * @param ficherosEscogidos que van a ser borrados
     * @param nombreFichero del archivo csv donde se va a guardar el registro de
     * ficheros borrados
     * @return la cantidad de ficheros que han sido eliminados
     */
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

    /**
     * Lista los ficheros de una ruta con recursividad aplicando un filtro según
     * el tipo de archivo
     *
     * @param tipo de archivo que se quiere filtrar, 0 para imagenes, 1 para
     * video, 2 para audio y 3 para texto
     * @param rutaQueSeQuiereListar ruta de la cual se van a listar los ficheros
     * filtrados
     * @return la lista de los ficheros filtrados
     */
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

    /**
     * Lista ficheros recursivamente previos a una fecha escogida
     *
     * @param opcionFechaMinima opción de la fecha, 0 para menos de 3 meses, 1
     * para menos de 6 meses y 2 para menos de 1 año
     * @param rutaQueSeQuiereListar ruta de los ficheros que se quieren listar
     * @return la lista de ficheros filtrados
     */
    public ArrayList<File> listarFicherosPorFechaRecursivamente(int opcionFechaMinima, String rutaQueSeQuiereListar) {
        Filtros filtro = new Filtros();
        FilenameFilter filtroEscogido = null;
        if (opcionFechaMinima == 0) {
            filtroEscogido = filtro.filtroPorFechaDeModificacion(2184L);
        } else if (opcionFechaMinima == 1) {
            filtroEscogido = filtro.filtroPorFechaDeModificacion(4368L);
        } else if (opcionFechaMinima == 2) {
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

    /**
     * Borra directorios vacios
     *
     * @param directoriosBorrados lista de los directorios
     * @return
     */
    public boolean borradoDeDirectoriosVacios(File[] directoriosBorrados) {
        for (File fichero : directoriosBorrados) {
            if (fichero.isDirectory()) {
                System.out.println(fichero);
                fichero.delete();
            }
        }
        return true;
    }

    /**
     * Compara los ficheros duplicados de una ruta
     *
     * @param rutaSeleccionada que se quiere comprobar
     * @return lista con los ficheros duplicados
     */
    public ArrayList<File> compararDuplicados(String rutaSeleccionada) {
        File[] ficherosDuplicados = listarArchivosRecursivamente(rutaSeleccionada);
        ArrayList<File> listaFicherosDuplicados = new ArrayList<File>();
        for (int contadorDeFicherosDuplicados = 0; contadorDeFicherosDuplicados < ficherosDuplicados.length; contadorDeFicherosDuplicados++) {
            if (!ficherosDuplicados[contadorDeFicherosDuplicados].isDirectory()) {
                for (int contadorDeListaParaComprobar = 0; contadorDeListaParaComprobar < ficherosDuplicados.length; contadorDeListaParaComprobar++) {

                    if (ficherosDuplicados[contadorDeFicherosDuplicados].getName().equals(ficherosDuplicados[contadorDeListaParaComprobar].getName())
                            && !ficherosDuplicados[contadorDeFicherosDuplicados].getAbsolutePath().equals(ficherosDuplicados[contadorDeListaParaComprobar].getAbsolutePath())
                            && ficherosDuplicados[contadorDeFicherosDuplicados].lastModified() == ficherosDuplicados[contadorDeListaParaComprobar].lastModified()
                            && ficherosDuplicados[contadorDeFicherosDuplicados].length() == ficherosDuplicados[contadorDeListaParaComprobar].length()) {
                        if (!listaFicherosDuplicados.contains(ficherosDuplicados[contadorDeListaParaComprobar]) && !ficherosDuplicados[contadorDeListaParaComprobar].isDirectory()) {
                            listaFicherosDuplicados.add(ficherosDuplicados[contadorDeListaParaComprobar]);
                        }
                    }
                }
            }
        }
        return listaFicherosDuplicados;
    }

    /**
     * Muestra un dialogo para seleccionar una carpeta
     *
     * @param pantalla
     * @return
     */
    public File escogerDirectorio(Component pantalla) {
        File ficheroEscogido = null;
        JFileChooser escogeSoloCarpetas = new JFileChooser();
        escogeSoloCarpetas.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int carpetaEscogida = escogeSoloCarpetas.showOpenDialog(pantalla);
        if (carpetaEscogida == JFileChooser.APPROVE_OPTION) {
            ficheroEscogido = escogeSoloCarpetas.getSelectedFile();
        }
        return ficheroEscogido;
    }

    /**
     * Metodo para comprimir los archivos y carpetas no vacias de una ruta.
     * 
     * @param carpetaDestino Donde se guardara el archivo .zip
     * @param nombreZip Nombre que recibirá el archivo .zip
     * @param carpetaAComprimir Carpeta que se desea comprimir
     * @return
     * @throws IOException 
     */
    public boolean comprimirCarpeta(File carpetaDestino, String nombreZip, File carpetaAComprimir) throws IOException {
        ZipOutputStream archivoZip = null;
        FileInputStream archivoQueLee = null;
        // ruta completa donde están los archivos a comprimir
        File carpetaComprimir = new File(carpetaAComprimir.getAbsolutePath());
        // valida si existe el directorio
        if (carpetaComprimir.exists()) {
            // lista los archivos que hay dentro del directorio
            File[] ficheros = listarArchivosRecursivamente(carpetaAComprimir.getAbsolutePath());
            System.out.println("Número de ficheros encontrados: " + ficheros.length);
            archivoZip = new ZipOutputStream(new FileOutputStream(nombreZip + ".zip"));
            for (int contadorDeFicheros = 0; contadorDeFicheros < ficheros.length; contadorDeFicheros++) {
                System.out.println("Nombre del fichero: " + ficheros[contadorDeFicheros].getName());
                //System.out.println("Ruta absoluta: " + ficheros[contadorDeFicheros].getAbsolutePath());
                if (!ficheros[contadorDeFicheros].isDirectory()) {
                    System.out.println("Comprimiendo.....");
                    //obtiene el archivo para comprimirlo
                    int leer;
                    byte[] buffer = new byte[4096];
                    ZipEntry entrada = new ZipEntry(ficheros[contadorDeFicheros].getAbsolutePath());
                    archivoQueLee = new FileInputStream(ficheros[contadorDeFicheros].getAbsolutePath());
                    archivoZip.putNextEntry(entrada);
                    while (0 < (leer = archivoQueLee.read(buffer))) {
                        archivoZip.write(buffer, 0, leer);
                    }
                }
            }
            archivoQueLee.close();
            archivoZip.close();
            System.out.println("Directorio de salida: " + carpetaDestino);
            return true;
        } else {
            System.out.println("No se encontró el directorio.");
            return false;
        }
    }
}
