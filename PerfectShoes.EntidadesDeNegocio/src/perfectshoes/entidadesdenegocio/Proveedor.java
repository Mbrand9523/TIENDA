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

public class Proveedor {
    private int id;
    private String nombre;
    private String apellido;
    private String razon_social;
    private String telefono;
    private String direccion;
    private int top_aux;
    private ArrayList<Proveedor> proveedor;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre, String apellido, String razon_social, String telefono, String direccion, int top_aux, ArrayList<Proveedor> proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.razon_social = razon_social;
        this.telefono = telefono;
        this.direccion = direccion;
        this.top_aux = top_aux;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(ArrayList<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }
    
}
