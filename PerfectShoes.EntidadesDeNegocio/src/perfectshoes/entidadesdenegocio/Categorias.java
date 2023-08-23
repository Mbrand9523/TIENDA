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

public class Categorias {
    private int id;
    private int idProductos;
    private String nombrescategorias;
    private Productos productos;  

    public Categorias() {
    }

    public Categorias(int id, int idProductos, String nombrescategorias, Productos productos) {
        this.id = id;
        this.idProductos = idProductos;
        this.nombrescategorias = nombrescategorias;
        this.productos = productos;
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

    public String getNombrescategorias() {
        return nombrescategorias;
    }

    public void setNombrescategorias(String nombrescategorias) {
        this.nombrescategorias = nombrescategorias;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

   
}
