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
import java.time.LocalDate;

public class DetalleVenta {
    private int id;
    private int idVentas;
    private int idProductos;
    private int idCategorias;
    private String nombre;
    private String marca;
    private Double cantidad;
    private Double precio;
    private Double subtotal;
    private Double Total;
    private Ventas ventas;
    private Productos productos;
    private Categorias categoria;
    private int top_aux;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int idVentas, int idProductos, int idCategorias, String nombre, String marca, Double cantidad, Double precio, Double subtotal, Double Total, Ventas ventas, Productos productos, Categorias categoria, int top_aux) {
        this.id = id;
        this.idVentas = idVentas;
        this.idProductos = idProductos;
        this.idCategorias = idCategorias;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.Total = Total;
        this.ventas = ventas;
        this.productos = productos;
        this.categoria = categoria;
        this.top_aux = top_aux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public int getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(int idCategorias) {
        this.idCategorias = idCategorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
}
