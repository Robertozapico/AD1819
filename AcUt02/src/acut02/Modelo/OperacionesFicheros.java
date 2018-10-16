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
            /*
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].toString());
                System.out.println(lista[i].length());

            }
             */
        }
        /*else {
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].toString());
                System.out.println(lista[i].length());

            }
        }*/

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
        /*EJEMPLO
        File file = new File("\\");
        FilenameFilter filtroGif = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".gif");
            }

        };*/
        File file = new File("/home/alumnop/Documentos");
        ArrayList<File> listaFicherosFiltrados = new ArrayList<File>();
        File[] listaFicheros;
        /*FilenameFilter filtroGif = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".gif");
            }

        };*/
        FilenameFilter filtroJpg = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".jpg");
            }

        };
        FilenameFilter filtroTiff = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".tiff");
            }

        };
        FilenameFilter filtroAvi = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".avi");
            }

        };
        FilenameFilter filtroMkv = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mkv");
            }

        };
        FilenameFilter filtroMp4 = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mp4");
            }

        };
        listaFicheros = file.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {

            if (filtro.accept(file, listaFicheros[i].getName())) {
                
                File[] listaFicherosGif = file.listFiles(filtro);
                for (File file1 : listaFicherosGif) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
                //listaDeFicheros.add(listaFicherosGif);
            } else if (filtro.equals(filtroJpg)) {
                File[] listaFicherosJpg = file.listFiles(filtroJpg);
                for (File file1 : listaFicherosJpg) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            } else if (filtro.equals(filtroTiff)) {
                File[] listaFicherosTiff = file.listFiles(filtroTiff);
                for (File file1 : listaFicherosTiff) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            } else if (filtro.equals(filtroMp4)) {
                File[] listaFicherosMp4 = file.listFiles(filtroMp4);
                for (File file1 : listaFicherosMp4) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            } else if (filtro.equals(filtroMkv)) {
                File[] listaFicherosMkv = file.listFiles(filtroMkv);
                for (File file1 : listaFicherosMkv) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            } else if (filtro.equals(filtroAvi)) {
                File[] listaFicherosAvi = file.listFiles(filtroAvi);
                for (File file1 : listaFicherosAvi) {
                    listaFicherosFiltrados.add(file1);
                }
                return listaFicherosFiltrados;
            }/*else{
                for(File file1 : listaFicherosFiltrados)
            }*/
        }
        return listaFicherosFiltrados;
    }

    /*Crear  los  siguientes  filtros  implementado  la  interface  FileNameFilter  (Se  puede  crear  una  clase  Filtros):  
    i.Filtro  ficheros  de  imágenes  (jpg,  gif,  tiff,  ...)  
    ii.Filtro  ficheros  de  video  (avi,  mp4,  mkv,  ...)  
    iii.Filtro  directorios.    iv.Filtro  ficheros  con  un  tamaño  mínimo.  
    v.Filtro  fichero  modificados  en  las  últimas  24  horas.    
    vi.Probar  el  correcto  funcionamiento  desde  un  método  main().  
    vii.Generar  Javadoc. */
}
