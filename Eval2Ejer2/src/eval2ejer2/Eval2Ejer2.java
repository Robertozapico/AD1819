/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval2ejer2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumnop
 */
public class Eval2Ejer2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        LogicaMetodos BDMetodos = new LogicaMetodos();

        System.out.println(BDMetodos.crearConexion());
        
        //BDMetodos.insertarPintor(7, "Aurelio", 25, 02, 1990, "Bodegon");

        System.out.println(BDMetodos.obtenerPintoresOrdenados(true));
        
        //Elimina un pintor, elimina un pintor a partir de su y id o de su nombre.
        //BDMetodos.eliminarPintorId(7);
        //BDMetodos.eliminarPintorNombre("Aurelio");
        System.out.println(BDMetodos.obtenerPintoresAnyo(1992));
        System.out.println(BDMetodos.obtenerCuadrosPintores(true));
        System.out.println(BDMetodos.obtenerCuadrosPintor("aaaaa"));
        
        System.out.println(BDMetodos.obtenerComentariosConInfo(30));
    }

}
