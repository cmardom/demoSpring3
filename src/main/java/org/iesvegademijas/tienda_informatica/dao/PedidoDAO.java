package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*VERSION DEL PROFESOR*/

/*
public interface PedidoDAO<Pedido> extends RepositoryBase<Pedido> {

    public Optional<Cliente> findClienteBy(int pedidoId);

    public Optional<Comercial> findComercialBy(int pedidoId);

    public static org.iesvegademijas.tienda_informatica.modelo.Pedido newPedido(ResultSet rs) throws SQLException {
        return new org.iesvegademijas.tienda_informatica.modelo.Pedido(rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoria")
                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getFloat("CO.comision")
                )
        );
    }

    public void delete(int id);

    public List<Pedido> getAll();
    public void update (Pedido pedido);
    public Optional<Pedido> find (int id);
    public void create (Pedido pedido);
}*/

public interface PedidoDAO {
    public void create(Pedido pedido);
    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);
    public void update (Pedido pedido);
    public void delete(int id);
    public List<Pedido> mostrarPedidosComercial(int id_comercial);



}
