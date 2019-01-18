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
        // We register the MySQL (MariaDB) driver
// Registramos el driver de MySQL (MariaDB)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection connection = null;
// Database connect
// Conectamos con la base de datos
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Pintores", "root", "root");
        if (connection != null) {
            System.out.println("EXITO");
        } else {
            System.out.println("FRACASO");
        }
        /*Retorna todos los pintores, (devuelve un ResultSet que contiene todos los nombres de los pintores y visualizarlo en 
        otro método),  también los puede devolver ordenados por nombre si el usuario lo dese*/

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Pintor VALUES " +
                            "('4231', 'Chimuelo', '1993-2-2', 'Oleo', 63453)") ;

        ResultSet rs = stmt.executeQuery("SELECT * FROM Pintor");
        System.out.println(rs.getString("Nombre"));

    }

}
