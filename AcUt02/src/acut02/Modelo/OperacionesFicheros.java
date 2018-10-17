/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnop
 */
public class OperacionesFicheros {
//http://bit.ly/JJCMAD2018
    //FALTA GENERAR JAVADOC
    //Ejercicio 1 y 2
    //Falta filtro de carpetas, tamaño y modificados en las ultimas 24h

    private File[] rutasFicheros;

    //Ejercicio 1.A
    public File[] ListarFicheros(String ruta, boolean ordenadosPorTamanio, boolean soloDirectorios) {
        File[] lista = null;

        if (ruta.equals("")) {
            rutasFicheros = File.listRoots();
            ruta = rutasFicheros[0].toString();
        }
        File archivos = new File(ruta);

        lista = archivos.listFiles();
        //HACER ESTAS EXCEPCIONES COMO PROPIAS
        if (archivos.isDirectory() == false) {
            try {
                throw new ExcepcionPersonalizada/*IllegalArgumentException*/("La ruta no es un directorio, no se puede listar");
            } catch (ExcepcionPersonalizada ex) {
                String respuesta = ex.getMessage();
            }
        }
        if (lista.length == 0) {
            try {
                throw new ExcepcionPersonalizada/*IllegalArgumentException*/("La carpeta está vacia");
            } catch (ExcepcionPersonalizada ex) {
                String respuesta = ex.getMessage();
            }
            /*throw new IllegalArgumentException("La carpeta está vacia");*/
        }
        if (ordenadosPorTamanio == true) {

            Arrays.sort(lista, new Comparator<File>() {
                @Override
                public int compare(File fichero1, File fichero2) {
                    if (fichero1.length() < fichero2.length()) {
                        return 1;
                    } else if (fichero1.length() > fichero2.length()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }

            });
        }
        if (soloDirectorios == true) {

            for (int i = 0; i < lista.length; i++) {
                if (lista[i].isDirectory() == true) {
                    System.out.println(lista[i].toString());
                    System.out.println(lista[i].length());
                }
            }
        } else {
            for (int i = 0; i < lista.length; i++) {

                System.out.println(lista[i].toString());
                System.out.println(lista[i].length());
            }
        }

        /* 
iv.Crear dos  excepciones personalizadas  
        Carpeta Vacia y NoEsUnDirectorioNoSePuedeListar v Probar el correcto funcionamiento desde un método main().Generar Javadoc.
         */
        return lista;
    }

    public int crearDirectorio(File rutaOrigen, ArrayList<String> listaDirectorios) {
        int contadorCreados = 0;
        if (rutaOrigen.exists() == false) {
            throw new IllegalArgumentException("La ruta no existe");
        }
        for (String listaDirectorio : listaDirectorios) {
            File nuevoDirectorio = new File(rutaOrigen + "/" + listaDirectorio.toString());
            if (nuevoDirectorio.exists()) {
                throw new IllegalArgumentException("El directorio " + listaDirectorio + " ya existe");
            }
            nuevoDirectorio.mkdir();
            contadorCreados++;
        }
        return contadorCreados;
    }

    //EJERCICIO 1.C
    //iv.Generar  Javadoc
    public int cambiarExtensionFicheros(String ruta, String extensionAntigua, String extensionNueva) throws ExcepcionPersonalizada {
        File file = new File(ruta);
        int ficheroCambiado = 0;
        File[] listaFicheros;
        listaFicheros = file.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {

            if (listaFicheros[i].getName().endsWith(extensionAntigua)) {

                String nombreDelFichero = listaFicheros[i].getName().substring(0, listaFicheros[i].getName().lastIndexOf('.'));
                nombreDelFichero += "." + extensionNueva;
                for (int j = 0; j < listaFicheros.length; j++) {

                    if (listaFicheros[j].getName().equals(nombreDelFichero)) {
                        throw new ExcepcionPersonalizada("Ya existe el archivo con ese nombre");
                    }
                }
                nombreDelFichero += "." + extensionNueva;
                listaFicheros[i].renameTo(new File(listaFicheros[i].getParentFile(), nombreDelFichero));
                ficheroCambiado++;
            }
        }
        System.out.println(file.listFiles());
        return ficheroCambiado;
    }

    public int cambiarExtensionFicheros(File file, String extensionAntigua, String extensionNueva) throws ExcepcionPersonalizada {
        int ficheroCambiado = 0;
        File[] listaFicheros;
        listaFicheros = file.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {

            if (listaFicheros[i].getName().endsWith(extensionAntigua)) {

                String nombreDelFichero = listaFicheros[i].getName().substring(0, listaFicheros[i].getName().lastIndexOf('.'));
                nombreDelFichero += "." + extensionNueva;
                for (int j = 0; j < listaFicheros.length; j++) {

                    if (listaFicheros[j].getName().equals(nombreDelFichero)) {
                        throw new ExcepcionPersonalizada("Ya existe el archivo con ese nombre");
                    }
                }
                nombreDelFichero += "." + extensionNueva;
                listaFicheros[i].renameTo(new File(listaFicheros[i].getParentFile(), nombreDelFichero));
                ficheroCambiado++;
            }
        }
        System.out.println(file.listFiles());
        return ficheroCambiado;
    }

    //Ejercicio 2
    public int fibonacci(int n) {
        if (n > 1) {
            return fibonacci(n - 1) + fibonacci(n - 2);
        } else if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else {
            System.out.println("Debes introducir un dato superior o igual a 1");
            return -1;
        }
    }
    //Factorial  

    //Recursividad
    public void listarDirectorio(String ruta) {
        File rutaDirectorio = new File(ruta);
        File[] ficheros = null;
        ficheros = rutaDirectorio.listFiles();
        int x;
        for (x = 0; x < ficheros.length; x++) {
            System.out.println(" " + ficheros[x].getName());
        }
        if (ficheros[x].isDirectory()) {
            String nuevo_separador;
            nuevo_separador = " ";
            listarDirectorio(ruta + ficheros[x]);
        }
        //return rutaDirectorio;
    }

    public void listarArchivosRecursivamente(String ruta) {
        File rutaFicheros = new File(ruta);
        File[] ficheros = rutaFicheros.listFiles();
        for (int i = 0; i < ficheros.length; i++) {
            if (ficheros[i].isDirectory() == false) {
                String guion = "-";
                for (int j = 0; j < ficheros[i].getParent().length(); j++) {
                    guion += "-";
                }
                System.out.println("\n" + "./" + guion + "/" + ficheros[i].getName());
            } else {
                System.out.println(ficheros[i].getAbsolutePath());
                listarArchivosRecursivamente(ficheros[i].getAbsolutePath());
            }
        }
    }

    public ArrayList<File> listarFicheros(FilenameFilter filtro) {
        //File file = new File("/home/alumnop/Documentos");
        File file = new File("C:/Prueba");
        ArrayList<File> listaFicherosFiltrados = new ArrayList<File>();
        File[] listaFicheros;
        listaFicheros = file.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {
            if (filtro.accept(file, listaFicheros[i].getName())) {
                File[] listaFicherosFiltro = file.listFiles(filtro);
                for (File file1 : listaFicherosFiltro) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            }
        }
        return listaFicherosFiltrados;
    }
    /*
    vii.Generar  Javadoc. */
}
