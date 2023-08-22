/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectshoes.entidadesdenegocio;

/**
 *
 * @author Carmen Estela
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class Ventas {
    private int id;
    private int idProductos;
    private LocalDate fecha;
    private int top_aux;
    private ArrayList<Productos> productos;
    private Productos producto;

    public Ventas() {
    }

    public Ventas(int id, int idProductos, LocalDate fecha, int top_aux, ArrayList<Productos> productos, Productos producto) {
        this.id = id;
        this.idProductos = idProductos;
        this.fecha = fecha;
        this.top_aux = top_aux;
        this.productos = productos;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
    
}
