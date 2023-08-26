/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectshoes.entidadesdenegocio;

/**
 *
 * @author Carmen Estela
 */
import java.util.ArrayList;

public class Productos {
     private int id;
     private int idProveedor;
     private int marca;
     private String nombre;
     private String cantidad;
     private Double precio;
     private String talla; 
     private Proveedor proveedor;
     private int top_aux;
     private ArrayList<Categorias> categorias;

    public Productos() {
    }

    public Productos(int id, int idProveedor, int idmarca, String nombre, String cantidad, Double precio, String talla, Proveedor proveedor, int top_aux, ArrayList<Categorias> categorias) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.marca = marca;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.talla = talla;
        this.proveedor = proveedor;
        this.top_aux = top_aux;
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdmarca() {
        return marca;
    }

    public void setIdmarca(int idmarca) {
        this.marca = idmarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categorias> categorias) {
        this.categorias = categorias;
    }

    public void setmarca(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
     

}

   
