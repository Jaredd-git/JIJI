/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloLibros;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Estudiante_PC19
 */
public class ControllerLibros {
    ModeloLibros mdlibros = new ModeloLibros();
    
    public static int ID;
    public String Fecha_Edicion;
    public String Fecha_Publicacion;
    public int NumPag;
    public int IdEstado;
    public int anio;
    public String titulo;
    public String resumen;
    public String ISBN;
    public int IdEditorial;
    public int IdCategoria;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFecha_Edicion() {
        return Fecha_Edicion;
    }

    public void setFecha_Edicion(String Fecha_Edicion) {
        this.Fecha_Edicion = Fecha_Edicion;
    }

    public String getFecha_Publicacion() {
        return Fecha_Publicacion;
    }

    public void setFecha_Publicacion(String Fecha_Publicacion) {
        this.Fecha_Publicacion = Fecha_Publicacion;
    }


    public int getNumPag() {
        return NumPag;
    }

    public void setNumPag(int NumPag) {
        this.NumPag = NumPag;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resume) {
        this.resumen = resumen;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getIdEditorial() {
        return IdEditorial;
    }

    public void setIdEditorial(int IdEditorial) {
        this.IdEditorial = IdEditorial;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public ControllerLibros(String Fecha_Edicion, String Fecha_Publicacion, int NumPag, int IdEstado, int anio, String titulo, String resumen, String ISBN, int IdEditorial, int IdCategoria) {
        this.Fecha_Edicion = Fecha_Edicion;
        this.Fecha_Publicacion = Fecha_Publicacion;
        this.NumPag = NumPag;
        this.IdEstado = IdEstado;
        this.anio = anio;
        this.titulo = titulo;
        this.resumen = resumen;
        this.ISBN = ISBN;
        this.IdEditorial = IdEditorial;
        this.IdCategoria = IdCategoria;
    }

    public ControllerLibros(int ID) {
        this.ID = ID;
    }

    public ControllerLibros() {
    }
    
    public boolean LibroNuevoController(){
        return mdlibros.Registrar(Fecha_Edicion, Fecha_Publicacion, NumPag, IdEstado, anio, titulo, resumen, ISBN, IdEditorial, IdCategoria);
    }
    
    public boolean LibroEliminarController(){
        return mdlibros.Eliminar(ID);
    }
    
    public boolean LibroActualizarController(){
        return mdlibros.Actualizar(Fecha_Edicion, Fecha_Publicacion, NumPag, IdEstado, anio, titulo, resumen, ISBN, IdEditorial, IdCategoria, ID);
    }
            
    public ResultSet CargarEstadoResultSet(){
        return mdlibros.CargarComboEstado();
    }
    
    public ResultSet CargarCategoriaResultSet(){
        return mdlibros.CargarComboCategoria();
    }
    
    public ResultSet CargarEditorialResultSet(){
        return mdlibros.CargarComboEditorial();
    }
     public ResultSet CargarLibrosResultSet(){
        return mdlibros.CargarTablaLibros();
    }
}
