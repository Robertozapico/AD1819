/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acut02.Modelo;

import java.io.File;
import java.io.FilenameFilter;

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

    public FilenameFilter filtritocarpetas(){
        
        return null;
        
    }
}
