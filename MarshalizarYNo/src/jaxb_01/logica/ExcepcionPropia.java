/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01.logica;

/**
 *
 * @author alumnop
 */
public class ExcepcionPropia {

    public static class ErrorFecha extends Exception {

        public ErrorFecha() {
            System.out.println("Fecha no comprendida entre el 1 de enero de 1989 y el 31 de diciembre de 2030");
        }

        public ErrorFecha(String msg) {
            super(msg);
        }
    }

}
