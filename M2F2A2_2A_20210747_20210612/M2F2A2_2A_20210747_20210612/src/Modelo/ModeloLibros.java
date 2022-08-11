/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante_PC19
 */

public class ModeloLibros {
    PreparedStatement ps;
    public ResultSet CargarTablaLibros(){
        try {
            ResultSet rs;
            Connection con = ModelConexion.getConnection();
            String query = "SELECT * FROM Libros";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    
    public ResultSet CargarComboEstado(){
        try {
            ResultSet rs;
            Connection con = ModelConexion.getConnection();
            String query = "SELECT * FROM Estado";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    
    public ResultSet CargarComboCategoria(){
        try {
            ResultSet rs;
            Connection con = ModelConexion.getConnection();
            String query = "SELECT * FROM Categorias";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    
    public ResultSet CargarComboEditorial(){
        try {
            ResultSet rs;
            Connection con = ModelConexion.getConnection();
            String query = "SELECT * FROM Editorial";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    public boolean Registrar(String Fecha_edicion, String Fecha_publicacion, int NumPag, int IdEstado, int anio, String titulo, String resumen, String ISBN, int IdEditorial, int IdCategoria){
        try {
            Connection con = ModelConexion.getConnection();
            String query = "INSERT INTO Libros VALUES (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, Fecha_edicion);
            ps.setString(2, Fecha_publicacion);
            ps.setInt(3, NumPag);
            ps.setInt(4, IdEstado);
            ps.setInt(5, anio);
            ps.setString(6, titulo);
            ps.setString(7, resumen);
            ps.setString(8, ISBN);
            ps.setInt(9, IdEditorial);
            ps.setInt(10, IdCategoria);
            if (ps.executeUpdate() == 1) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos"+ e.toString());
            return false;
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error critico" + ex.toString());
            return false;
        }
    }
    
    public Boolean Actualizar(String Fecha_edicion, String Fecha_publicacion, int NumPag, int IdEstado, int anio, String titulo, String resumen, String ISBN, int IdEditorial, int IdCategoria, int ID) {
        try {
            Connection con = ModelConexion.getConnection();
            String query = "UPDATE Libros SET fechaEdicion=?, fechaPublicacion=?, NumPaginas=?, idestado=?, anio=?, titulo=?, resumen=?, isbn=?, idEditorial=?, idCategoria=? where idLibro=?";
            ps = con.prepareStatement(query);
            ps.setString(1, Fecha_edicion);
            ps.setString(2, Fecha_publicacion);
            ps.setInt(3, NumPag);
            ps.setInt(4, IdEstado);
            ps.setInt(5, anio);
            ps.setString(6, titulo);
            ps.setString(7, resumen);
            ps.setString(8, ISBN);
            ps.setInt(9, IdEditorial);
            ps.setInt(10, IdCategoria);
            ps.setInt(11, ID);
            if (ps.executeUpdate() == 1) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error no se pudo actualizar el registro "+e.toString());
            return false;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error critico" + ex.toString());
            return false;
        }
    }
    
    public boolean Eliminar(int ID){        
        try {
            Connection con = ModelConexion.getConnection();
            String query = "DELETE FROM Libros WHERE idLibro = " + ID;
            ps = con.prepareStatement(query);
            if (ps.executeUpdate() == 1) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error no se pudo eliminar el registro "+e.toString());
            return false;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error critico" + ex.toString());
            return false;     
        }
    }
}
