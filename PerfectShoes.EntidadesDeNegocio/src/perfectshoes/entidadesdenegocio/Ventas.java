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
    private int Id;
    private int IdClientes;
    private String Fecha;
    private int top_aux;
    private Ventas ventas;

    public Ventas() {
    }

    public Ventas(int Id, int IdClientes, String Fecha, int top_aux, Ventas ventas) {
        this.Id = Id;
        this.IdClientes = IdClientes;
        this.Fecha = Fecha;
        this.top_aux = top_aux;
        this.ventas = ventas;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdClientes() {
        return IdClientes;
    }

    public void setIdClientes(int IdClientes) {
        this.IdClientes = IdClientes;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
    
}
