package org.iesvegademijas.tienda_informatica.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
public class Pedido {

    private int id;
    private double total;
    private Date fecha;
    private Cliente cliente;
    private Comercial comercial;

    public Pedido() {

    }

    public Pedido(int id, double total, Date fecha, Cliente cliente, Comercial comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                ", comercial=" + comercial +
                '}';
    }
}