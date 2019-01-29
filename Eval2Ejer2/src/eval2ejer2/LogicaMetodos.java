/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval2ejer2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnop
 */
public class LogicaMetodos {

    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;

    public String crearConexion() {
        // Registramos el driver de MySQL (MariaDB)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }

        try {
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pintores", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            return "EXITO";
        } else {
            return "FRACASO";
        }
    }

    //EJERCICIO B
    public boolean insertarPintor(int idPintor, String nombre, int diaNacimiento, int mesNacimiento, int anioNacimiento, String estilo) {
        String fechaNacimiento = anioNacimiento + "-" + mesNacimiento + "-" + diaNacimiento;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO Pintor VALUES(" + idPintor + ", '" + nombre + "', '" + fechaNacimiento + "', '" + estilo + "')");
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
//stmt.executeUpdate("INSERT INTO Pintor VALUES(3, 'aaaaa', '1993-3-25', 'Oleo')");
        return true;
    }

    //Ejercicio A
    public ArrayList obtenerPintoresOrdenados(boolean ordenados) {
        List<Pintor> pintores = new ArrayList<Pintor>();
        try {
            stmt = connection.createStatement();
            if (ordenados) {
                rs = stmt.executeQuery("SELECT * FROM Pintor ORDER BY nombre");
            } else {
                rs = stmt.executeQuery("SELECT * FROM Pintor");
            }
            while (rs.next()) {
                //System.out.println(rs.getString("nombre"));
                int id = rs.getInt("id_Pintor");
                String nombrePintor = rs.getString("nombre");
                Date fechaNacimiento = rs.getDate("anio_nacimiento");
                String estiloPintor = rs.getString("estilo");
                Pintor pintor = new Pintor(id, nombrePintor, fechaNacimiento, estiloPintor);
                pintores.add(pintor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList) pintores;
    }

    //Ejercicio 1-C
    public int eliminarPintorNombre(String nombre) {
        int filasAfectadas = 0;
        PreparedStatement ps = null;
        try {
            //stmt = connection.createStatement();
            ps = connection.prepareStatement("DELETE FROM Pintor WHERE nombre='" + nombre + "'");

            //rs = stmt.executeQuery("DELETE FROM Pintor WHERE id_Pintor=7");
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filasAfectadas;
    }

    public int eliminarPintorId(int id) {
        int filasAfectadas = 0;
        PreparedStatement ps = null;
        try {
            //stmt = connection.createStatement();
            ps = connection.prepareStatement("DELETE FROM Pintor WHERE id_Pintor=" + id);
            //rs = stmt.executeQuery("DELETE FROM Pintor WHERE id_Pintor=7");
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filasAfectadas;
    }

    //1-D
    public ArrayList obtenerPintoresAnyo(int diaNacimiento, int mesNacimiento, int anioNacimiento) {
        List<Pintor> pintores = new ArrayList<Pintor>();
        PreparedStatement ps = null;
        String fechaNacimiento = anioNacimiento + "-" + mesNacimiento + "-" + diaNacimiento;
        try {
            stmt = connection.createStatement();
            ps = connection.prepareStatement("SELECT * FROM Pintor WHERE anio_nacimiento='" + fechaNacimiento + "'");
            rs=ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_Pintor");
                String nombrePintor = rs.getString("nombre");
                Date fechaNacimientoDate = rs.getDate("anio_nacimiento");
                String estiloPintor = rs.getString("estilo");
                Pintor pintor = new Pintor(id, nombrePintor, fechaNacimientoDate, estiloPintor);
                pintores.add(pintor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (ArrayList) pintores;
    }
}
