/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

/**
 *
 * @author zapia
 */
public class Filtros {

    public FilenameFilter filtroImagenes() {
        FilenameFilter filtroDeImagenes = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".gif") || name.endsWith(".jpg")
                        || name.endsWith(".png") || name.endsWith(".bmp")
                        || name.endsWith(".jpeg") || name.endsWith(".raw");
            }
        };
        return filtroDeImagenes;
    }

    public FilenameFilter filtroVideo() {
        FilenameFilter filtroDeVideo = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".avi") || name.endsWith(".mpeg")
                        || name.endsWith(".mov") || name.endsWith(".mp4")
                        || name.endsWith(".wmv") || name.endsWith(".webm")
                        || name.endsWith(".flv");
            }
        };
        return filtroDeVideo;
    }

    public FilenameFilter filtroAudio() {
        FilenameFilter filtroDeAudio = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".mp3") || name.endsWith(".wav")
                        || name.endsWith(".aac");
            }
        };
        return filtroDeAudio;
    }

    public FilenameFilter filtroTexto() {
        FilenameFilter filtroDeTexto = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".pdf") || name.endsWith(".doc")
                        || name.endsWith(".docx") || name.endsWith(".txt")
                        || name.endsWith(".odt") || name.endsWith(".csv")
                        || name.endsWith(".xls") || name.endsWith(".xlsx")
                        || name.endsWith(".xml") || name.endsWith(".html");
            }
        };
        return filtroDeTexto;
    }

    public FilenameFilter filtroPorFechaDeModificacion(Long totalHoras) {
        FilenameFilter filtroFecha = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                Date fechaHoy = new Date();
                Long timeActual = fechaHoy.getTime();
                System.out.println(file.lastModified() + "," + (timeActual - (1000 * 3600 * totalHoras)));
                Date fechatresmeses = new Date(timeActual - (1000 * 3600 * totalHoras));
                System.out.println(fechatresmeses);
                if (file.lastModified() > (timeActual - (1000 * 3600 * totalHoras))) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        return filtroFecha;
    }
}