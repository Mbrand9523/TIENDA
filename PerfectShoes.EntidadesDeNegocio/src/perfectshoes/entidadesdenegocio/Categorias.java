/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectshoes.entidadesdenegocio;

/**
 *
 * @author Carmen Estela
 */

public class Categorias {
    private int id;
    private int idProductos;
    private String nombrescategorias;
    private String productos;  
    private int top_aux;

    public Categorias() {
    }

    public Categorias(int id, int idProductos, String nombrescategorias, String productos, int top_aux) {
        this.id = id;
        this.idProductos = idProductos;
        this.nombrescategorias = nombrescategorias;
        this.productos = productos;
        this.top_aux = top_aux;
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

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}
