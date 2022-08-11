/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante_PC19
 */
public class ModelConexion {
    public static Connection getConnection(){
        Connection con;
        try {
            String url = "jdbc:sqlserver://localhost:1433;"
                + "database = dbBiblioteca;"
                + "user = sa;"
                + "password = 1234;";
            con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
}
