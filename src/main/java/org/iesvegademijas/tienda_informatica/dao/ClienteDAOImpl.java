package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements  ClienteDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public synchronized void create(Cliente cliente){
        jdbcTemplate.update("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoria) VALUES (?, ?, ?, ?, ?)"
                , cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria());
    }

    @Override
    public List<Cliente> getAll(){
        /*FALLO > No reconoce id en la query*/
        List<Cliente> listaCli = jdbcTemplate.query("SELECT * FROM cliente", (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre")
                , rs.getString("apellido1"), rs.getString("apellido2")
                , rs.getString("ciudad"), rs.getInt("categoria")));
        return listaCli;
    }

    @Override
    public Optional<Cliente> find(int id){
        Cliente cli = jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                , (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre")
                                , rs.getString("apellido1"), rs.getString("apellido2")
                                , rs.getString("ciudad"), rs.getInt("categoria"))
        ,id);

        if (cli != null) return Optional.of(cli);
        else return Optional.empty();
    }

    @Override
    public void update(Cliente cliente){
        int rows = jdbcTemplate.update("""
										UPDATE cliente SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														ciudad = ?,
														categoria = ?  
												WHERE id = ?
										""", cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getId());

        String consola = rows > 0 ? "Update de cliente con " + rows + " registros actualizados" : "No se han realizado updates de cliente";
        System.out.println(consola);
    }

    @Override
    public void delete(int id){
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        String consola = rows > 0 ? "Delete de cliente con " + rows + " registros actualizados" : "No se han realizado deletes de cliente";
        System.out.println(consola);

    }

}
