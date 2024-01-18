package org.iesvegademijas.tienda_informatica.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvegademijas.tienda_informatica.dao.ClienteDAOImpl;

import java.util.Date;
@Data
public class Pedido {

    private int id;
    private double total;
    private Date fecha;
    /*private Cliente cliente;
    private Comercial comercial;
    */
    private int id_cliente;
    private int id_comercial;
    public Pedido() {

    }

   /* public Pedido(int id, double total, Date fecha, Cliente cliente, Comercial comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }
*/
    public Pedido (int id, double total, Date fecha, int id_cliente, int id_comercial){
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
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

   /* public Cliente getCliente() {
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
    }*/

  /*  @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                ", comercial=" + comercial +
                '}';
    }*/
}