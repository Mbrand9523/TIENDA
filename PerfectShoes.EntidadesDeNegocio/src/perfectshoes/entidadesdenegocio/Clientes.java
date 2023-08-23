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

public class Clientes {
    private int id;
    private int idVentas;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String dui;
    private Ventas ventas;

    public Clientes() {
    }

    public Clientes(int id, int idVentas, String nombre, String apellido, String telefono, String direccion, String dui, Ventas ventas) {
        this.id = id;
        this.idVentas = idVentas;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.dui = dui;
        this.ventas = ventas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
    

    
}
