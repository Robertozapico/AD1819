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

    //Ejercicio 1.A
    /**
     *
     * @param rutaParaListar
     * @param quiereOrdenarPorTamanio
     * @param quiereSoloDirectorios
     * @return
     */
    public File[] ListarFicheros(String rutaParaListar, boolean quiereOrdenarPorTamanio, boolean quiereSoloDirectorios) {
        File[] listaDeFicherosParaListar = null;
        File[] rutasDeFicherosParaLaLista;
        if (rutaParaListar.equals("")) {
            rutasDeFicherosParaLaLista = File.listRoots();
            rutaParaListar = rutasDeFicherosParaLaLista[0].toString();
        }
        File rutaParaListarConvertidaAFile = new File(rutaParaListar);

        listaDeFicherosParaListar = rutaParaListarConvertidaAFile.listFiles();
        if (rutaParaListarConvertidaAFile.isDirectory() == false) {
            try {
                throw new ExcepcionPersonalizada("La ruta no es un directorio, no se puede listar");
            } catch (ExcepcionPersonalizada ex) {
                String respuestaALaExcepcion = ex.getMessage();
            }
        }
        if (listaDeFicherosParaListar.length == 0) {
            try {
                throw new ExcepcionPersonalizada("La carpeta está vacia");
            } catch (ExcepcionPersonalizada ex) {
                String respuesta = ex.getMessage();
            }
        }
        if (quiereOrdenarPorTamanio == true) {

            Arrays.sort(listaDeFicherosParaListar, new Comparator<File>() {
                @Override
                public int compare(File fichero1Comparado, File fichero2Comparado) {
                    if (fichero1Comparado.length() < fichero2Comparado.length()) {
                        return 1;
                    } else if (fichero1Comparado.length() > fichero2Comparado.length()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }

            });
        }
        if (quiereSoloDirectorios == true) {

            for (int i = 0; i < listaDeFicherosParaListar.length; i++) {
                if (listaDeFicherosParaListar[i].isDirectory() == true) {
                    System.out.println(listaDeFicherosParaListar[i].toString());
                    System.out.println(listaDeFicherosParaListar[i].length());
                }
            }
        } else {
            for (int i = 0; i < listaDeFicherosParaListar.length; i++) {

                System.out.println(listaDeFicherosParaListar[i].toString());
                System.out.println(listaDeFicherosParaListar[i].length());
            }
        }
        return listaDeFicherosParaListar;
    }

    /**
     *
     * @param rutaOrigenDondeCrearLosDirectorios
     * @param listaDirectoriosQueSeQuierenCrear
     * @return
     */
    public int crearDirectorio(File rutaOrigenDondeCrearLosDirectorios, ArrayList<String> listaDirectoriosQueSeQuierenCrear) {
        int contadorDirectoriosCreados = 0;
        if (rutaOrigenDondeCrearLosDirectorios.exists() == false) {
            throw new IllegalArgumentException("La ruta no existe");
        }
        for (String directorioParaCrear : listaDirectoriosQueSeQuierenCrear) {
            File nuevoDirectorioQueSeQuiereCrear = new File(rutaOrigenDondeCrearLosDirectorios + "/" + directorioParaCrear.toString());
            if (nuevoDirectorioQueSeQuiereCrear.exists()) {
                throw new IllegalArgumentException("El directorio " + directorioParaCrear + " ya existe");
            }
            nuevoDirectorioQueSeQuiereCrear.mkdir();
            contadorDirectoriosCreados++;
        }
        return contadorDirectoriosCreados;
    }

    //EJERCICIO 1.C
    //iv.Generar  Javadoc
/**
 * 
 * @param rutaDeLosFicherosQueQuieresCambiarLaExtension
 * @param extensionActualDeLosFicheros
 * @param extensionQueSeQuierePonerALosFicheros
 * @return
 * @throws ExcepcionPersonalizada 
 */
    public int cambiarExtensionFicheros(String rutaDeLosFicherosQueQuieresCambiarLaExtension, String extensionActualDeLosFicheros, String extensionQueSeQuierePonerALosFicheros) throws ExcepcionPersonalizada {
        File rutaConvertidoAFile = new File(rutaDeLosFicherosQueQuieresCambiarLaExtension);
        int cantidadDeFicherosCambiados = 0;
        File[] listaDeFicherosDeLaRuta = rutaConvertidoAFile.listFiles();
        for (int ficheroLeido = 0; ficheroLeido < listaDeFicherosDeLaRuta.length; ficheroLeido++) {

            if (listaDeFicherosDeLaRuta[ficheroLeido].getName().endsWith(extensionActualDeLosFicheros)) {

                String stringNombreDelFicheroParaCambiarExtension = listaDeFicherosDeLaRuta[ficheroLeido].getName().substring(0, listaDeFicherosDeLaRuta[ficheroLeido].getName().lastIndexOf('.'));
                stringNombreDelFicheroParaCambiarExtension += "." + extensionQueSeQuierePonerALosFicheros;
                for (int contadorFicheroLeidoParaComprobarQueNoSeCambia = 0; contadorFicheroLeidoParaComprobarQueNoSeCambia < listaDeFicherosDeLaRuta.length; contadorFicheroLeidoParaComprobarQueNoSeCambia++) {

                    if (listaDeFicherosDeLaRuta[contadorFicheroLeidoParaComprobarQueNoSeCambia].getName().equals(stringNombreDelFicheroParaCambiarExtension)) {
                        throw new ExcepcionPersonalizada("Ya existe el archivo con ese nombre");
                    }
                }
                stringNombreDelFicheroParaCambiarExtension += "." + extensionQueSeQuierePonerALosFicheros;
                listaDeFicherosDeLaRuta[ficheroLeido].renameTo(new File(listaDeFicherosDeLaRuta[ficheroLeido].getParentFile(), stringNombreDelFicheroParaCambiarExtension));
                cantidadDeFicherosCambiados++;
            }
        }
        System.out.println(rutaConvertidoAFile.listFiles());
        return cantidadDeFicherosCambiados;
    }

    public int cambiarExtensionFicheros(File rutaDelosFicherosQueQuieresCambiarLaExtension, String extensionActualDeLosFicheros, String extensionQueSeQuierePonerALosFicheros) throws ExcepcionPersonalizada {
        int cantidadDeFicherosCambiados = 0;
        File[] listaFicheros = rutaDelosFicherosQueQuieresCambiarLaExtension.listFiles();
        for (int i = 0; i < listaFicheros.length; i++) {

            if (listaFicheros[i].getName().endsWith(extensionActualDeLosFicheros)) {

                String nombreDelFichero = listaFicheros[i].getName().substring(0, listaFicheros[i].getName().lastIndexOf('.'));
                nombreDelFichero += "." + extensionQueSeQuierePonerALosFicheros;
                for (int j = 0; j < listaFicheros.length; j++) {

                    if (listaFicheros[j].getName().equals(nombreDelFichero)) {
                        throw new ExcepcionPersonalizada("Ya existe el archivo con ese nombre");
                    }
                }
                nombreDelFichero += "." + extensionQueSeQuierePonerALosFicheros;
                listaFicheros[i].renameTo(new File(listaFicheros[i].getParentFile(), nombreDelFichero));
                cantidadDeFicherosCambiados++;
            }
        }
        System.out.println(rutaDelosFicherosQueQuieresCambiarLaExtension.listFiles());
        return cantidadDeFicherosCambiados;
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
