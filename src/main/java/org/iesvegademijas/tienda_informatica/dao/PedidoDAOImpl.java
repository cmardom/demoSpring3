package org.iesvegademijas.tienda_informatica.dao;

import lombok.extern.slf4j.Slf4j;

import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO<Pedido>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

   */
/* @Override
    public Optional<Pedido> findPedidoBy(int pedidoId) {

        Pedido pedido = this.jdbcTemplate.queryForObject("""
                            select C.* from pedido P join pedido C on P 
                """
                , (rs, rowNum) -> PedidoDAO.newPedido(rs)
        );

        return null;
    }*//*


    @Override
    public Optional<Pedido> findPedidoBy(int pedidoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Comercial> findComercialBy(int pedidoId) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void create(Pedido pedido) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        insert into pedido ( total, fecha, id_pedido, id_comercial)
                        values (?, ?, ?, ?);
                        """, new String[] { "id" });
            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setInt(idx++, pedido.getPedido().getId());
            ps.setInt(idx++, pedido.getComercial().getId());
            return ps;
        },keyHolder);

        log.info("Filas creadas {}", rows);
        log.debug("Pedido con id = {} grabado correctamente",keyHolder.getKey().intValue());

        pedido.setId(keyHolder.getKey().intValue());

    }

    @Override
    public List<Pedido> getAll() {

        List<Pedido> listPedido = this.jdbcTemplate.query("""
                SELECT * FROM  pedido P left join pedido C on  P.id_pedido = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                """, (rs, rowNum) -> PedidoDAO.newPedido(rs)
        );

        return listPedido;
    }

    @Override
    public Optional<Pedido> find(int id) {

        Pedido pedido= this.jdbcTemplate.queryForObject("""
                    select * from pedido P left join pedido C on  P.id_pedido = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                                        WHERE P.id = ?
                """, (rs, rowNum) -> PedidoDAO.newPedido(rs), id);

        if (pedido != null) return Optional.of(pedido);
        log.debug("No encontrado pedido con id {} devolviendo Optional.empty()", id);
        return Optional.empty();
    }

    @Override
    public void update(Pedido pedido) {

        this.jdbcTemplate.update("""
                      update pedido set total = ?, fecha = ?, id_pedido = ?, id_comercial = ? where id = ?
                    """, pedido.getTotal(), pedido.getFecha(), pedido.getPedido().getId(), pedido.getComercial().getId(), pedido.getId());

    }

    @Override
    public void delete(long id) {

        this.jdbcTemplate.update("""
                            delete from pedido where id = ?\s
                            """
                , id
        );

    }
}*/


 @Repository
public class PedidoDAOImpl implements PedidoDAO {

     @Autowired
     private JdbcTemplate jdbcTemplate;

     @Override
     public synchronized void create(Pedido pedido) {
         jdbcTemplate.update("INSERT INTO pedido (total, fecha, id_cliente, id_comercial)" +
                         "VALUES (?, ?, ?, ?)"
                 , pedido.getTotal()
                 , pedido.getFecha()
                 , pedido.getId_cliente()
                 , pedido.getId_comercial());
     }


     @Override
     public List<Pedido> getAll() {


         List<Pedido> listaPed = jdbcTemplate.query("SELECT * FROM pedido", (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total")
                 , rs.getDate("fecha"), rs.getInt("id_cliente"), rs.getInt("id_comercial")));
         return listaPed;
     }


     @Override
     public Optional<Pedido> find(int id) {
         Pedido ped = jdbcTemplate
                 .queryForObject("SELECT * FROM pedido WHERE id = ?"
                         , (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total")
                                 , rs.getDate("fecha"), rs.getInt("id_cliente")
                                 , rs.getInt("id_comercial"))
                         , id);

         if (ped != null) return Optional.of(ped);
         else return Optional.empty();
     }


     @Override
     public void update(Pedido pedido) {
         int rows = jdbcTemplate.update("""
										
                         UPDATE pedido SET 
														total = ?, 
														fecha = ? 
												WHERE id = ?
										""", pedido.getTotal()
                 , pedido.getFecha());

         String consola = rows > 0 ? "Update de pedido con " + rows + " registros actualizados" : "No se han realizado updates de pedido";
         System.out.println(consola);
     }

     @Override
     public void delete(int id){
         int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);



         String consola = rows > 0 ? "Delete de pedido con " + rows + " registros actualizados" : "No se han realizado deletes de pedido";
         System.out.println(consola);

     }

     public List<Pedido> mostrarPedidosComercial(int id_comercial) {
         List<Pedido> ped = jdbcTemplate.query("SELECT * from pedido p inner join comercial c on p.id_comercial = c.id where p.id_comercial = ?"
                 , (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total")
                         , rs.getDate("fecha"), rs.getInt("id_cliente"), rs.getInt("id_comercial")), id_comercial);


        //cambiar por inner join para que salgan los comerciales que no tienen pedidos (pero no pueden salir porque no puede haber pedidos sin comerciales)

         return ped;

     }

     public List<Pedido> mostrarPedidosCliente(int id_cliente) {

         List<Pedido> ped = jdbcTemplate.query("SELECT * from pedido p inner join cliente c on p.id_comercial = c.id where p.id_cliente = ?"
                 , (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total")
                         , rs.getDate("fecha"), rs.getInt("id_cliente"), rs.getInt("id_comercial")), id_cliente);

         return ped;

     }


 }
