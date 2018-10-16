/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author alumnop
 */
public class Filtros {

    public FilenameFilter filtroGif() {
        FilenameFilter filtroGif = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".gif");
            }

        };
        return filtroGif;
    }

    public FilenameFilter filtroJpg() {
        FilenameFilter filtroJpg = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".jpg");
            }

        };
        return filtroJpg;
    }

    public FilenameFilter filtroTiff() {
        FilenameFilter filtroTiff = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".tiff");
            }

        };
        return filtroTiff;
    }

    public FilenameFilter filtroAvi() {
        FilenameFilter filtroAvi = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".avi");
            }

        };
        return filtroAvi;
    }

    public FilenameFilter filtroMp4() {
        FilenameFilter filtroMp4 = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mp4");
            }

        };
        return filtroMp4;
    }

    public FilenameFilter filtroMkv() {
        FilenameFilter filtroMkv = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mkv");
            }

        };
        return filtroMkv;
    }

    public FilenameFilter filtroCarpetas() {
        FilenameFilter filtroCarpetas = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                File ficheroCarpeta = new File(file + "/" + name);
                return ficheroCarpeta.isDirectory();
            }
        };
        return filtroCarpetas;

    }

    public FilenameFilter filtroPorTamanno() {
        System.out.println("Escribe el tamaño en bytes");
        Scanner miTeclado = new Scanner(System.in);
        int tamannoArchivos = miTeclado.nextInt();
        FilenameFilter filtroCarpetas = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                File ficheroCarpeta = new File(file + "/" + name);
                System.out.println("Hecho por tamaño");
                return ficheroCarpeta.length() >= tamannoArchivos;
            }
        };
        return filtroCarpetas;

    }

    public FilenameFilter filtroPorFechaDeModificacion() {
        FilenameFilter filtroCarpetas = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                File ficheroCarpeta = new File(file + "/" + name);
                Date fecha = new Date(ficheroCarpeta.lastModified());
                
                Date fechaPasada = yesterday();
                return fechaPasada.before(fecha);
            }
        };
        return filtroCarpetas;
    }

    /*PA COGER LA FECHA DE AYER*/
    public Date yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

}
