package org.iesvegademijas.tienda_informatica.modelo;

import java.util.Date;

public class Pedido {
    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
    private int id_comercial;

    public Pedido(int id, double total, Date fecha, int id_cliente, int id_comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }

    public Pedido() {
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_comercial() {
        return id_comercial;
    }

    public void setId_comercial(int id_comercial) {
        this.id_comercial = id_comercial;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", id_cliente=" + id_cliente +
                ", id_comercial=" + id_comercial +
                '}';
    }
}
