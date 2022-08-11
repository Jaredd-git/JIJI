/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModelConexion;
import java.sql.Connection;

/**
 *
 * @author Estudiante_PC19
 */
public class ControladorConexion {
    public static Connection getConnectionController(){
        return ModelConexion.getConnection();
    }
}
