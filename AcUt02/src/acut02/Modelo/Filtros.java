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

    
    
    
    /**
     *
     * @return
     */
    public FilenameFilter filtroGif() {
        FilenameFilter filtroGif = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".gif");
            }

        };
        return filtroGif;
    }


    /**
     *
     * @return
     */
    public FilenameFilter filtroJpg() {
        FilenameFilter filtroJpg = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".jpg");
            }

        };
        return filtroJpg;
    }

    /**
     *
     * @return
     */
    public FilenameFilter filtroTiff() {
        FilenameFilter filtroTiff = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".tiff");
            }

        };
        return filtroTiff;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public FilenameFilter filtroMkv() {
        FilenameFilter filtroMkv = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mkv");
            }

        };
        return filtroMkv;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public FilenameFilter filtroPorModificacionEnLasUltimas24h() {
        FilenameFilter filtroDia = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                File ficheroAFiltrar = new File(file + "/" + name);
                Date fechaDeUltimaModificacionDelFichero = new Date(ficheroAFiltrar.lastModified());

                Date fechaPasada = fechaDeAyer();
                return fechaPasada.before(fechaDeUltimaModificacionDelFichero);
            }
        };
        return filtroDia;
    }

    /**
     *
     * @param totalHoras
     * @param totalMinutos
     * @param totalsegundos
     * @return
     */
    public FilenameFilter filtroPorFechaDeModificacion(int totalHoras, int totalMinutos, int totalsegundos) {
        FilenameFilter filtroDias = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                File ficheroCarpeta = new File(file + "/" + name);

                Date fechaHoy = null;

                long timeActual = fechaHoy.getTime();

                if ((ficheroCarpeta.lastModified() - timeActual) < (1000 * 3600 * totalHoras + 1000 * 60 * totalMinutos + 1000 * totalsegundos)) {
                    return true;

                }
                return true;

            }
        };
        return filtroDias;
    }

    /**
     *
     * @return
     */
    public Date fechaDeAyer() {
        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, -1);
        return fecha.getTime();
    }

}
