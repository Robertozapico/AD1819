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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            String consulta = "INSERT INTO Pintor VALUES(" + idPintor + ", '" + nombre + "', '" + fechaNacimiento + "', '" + estilo + "')";
            stmt.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
//stmt.executeUpdate("INSERT INTO Pintor VALUES(3, 'aaaaa', '1993-3-25', 'Oleo')");
        return true;
    }

    //Ejercicio A
    public ArrayList obtenerPintoresOrdenados(boolean ordenados) {
        List<Pintor> pintores = new ArrayList<Pintor>();
        String consulta;
        try {
            stmt = connection.createStatement();
            if (ordenados) {
                consulta = "SELECT * FROM Pintor ORDER BY nombre";
                rs = stmt.executeQuery(consulta);
            } else {
                consulta = "SELECT * FROM Pintor";
                rs = stmt.executeQuery(consulta);
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
            String consulta = "DELETE FROM Pintor WHERE nombre='" + nombre + "'";
            ps = connection.prepareStatement(consulta);

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
            String consulta = "DELETE FROM Pintor WHERE id_Pintor=" + id;
            ps = connection.prepareStatement(consulta);
            //rs = stmt.executeQuery("DELETE FROM Pintor WHERE id_Pintor=7");
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filasAfectadas;
    }

    //1-D
    public ArrayList obtenerPintoresAnyo(int anioNacimiento) {
        List<Pintor> pintores = new ArrayList<Pintor>();
        PreparedStatement ps = null;
        String fechaNacimientoMin = anioNacimiento + "-01-01";
        String fechaNacimientoMax = anioNacimiento + "-12-31";
        try {
            stmt = connection.createStatement();
            
            String consulta = "SELECT * FROM Pintor WHERE anio_nacimiento between'" + fechaNacimientoMin + "' AND '"+fechaNacimientoMax+"'";
            ps = connection.prepareStatement(consulta);
            rs = ps.executeQuery();
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

    /*Retorna todos los cuadros seguidos del  nombre del autor.  
    (Devolver un ResultSet que contengatodos títulos y los nombres de los pintores y visualizarlo en otro método),  
    también los puede devolver ordenados por titulo si el usuario lo desea.*/
   /* public void obtenerInfoCuadros(boolean ordenado) {
        Map<Integer, Pintor> cuadros = new HashMap<Integer, Pintor>();
        PreparedStatement ps = null;

        try {
            stmt = connection.createStatement();
            String consulta = "SELECT * FROM pintores.Pintor where Pintor.id_Pintor=(SELECT id_autor from pintores.Cuadro)";
            ps = connection.prepareStatement(consulta);
            rs = ps.executeQuery();
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
        }*/
    }
