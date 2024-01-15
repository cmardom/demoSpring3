package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComercialDAOImpl implements ComercialDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public synchronized void create(Comercial comercial){
        jdbcTemplate.update("INSERT INTO comercial (nombre) VALUES (?)", comercial.getNombre());
    }

    @Override
    public List<Comercial> getAll(){
        List<Comercial> listaCom = jdbcTemplate.query("SELECT * FROM comercial", (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre")
                        , rs.getString("apellido1"), rs.getString("apellido2")
                        , rs.getFloat("comision")));
        return listaCom;

    }

    @Override
    public Optional<Comercial> find(int id){
        Comercial com = jdbcTemplate.queryForObject("SELECT * FROM comercial WHERE id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre")
                        , rs.getString("apellido1"), rs.getString("apellido2")
                        , rs.getFloat("comision"))
                ,id);
        if (com != null) return Optional.of(com);
        else return Optional.empty();
    }

    @Override
    public void update(Comercial comercial){
        int rows = jdbcTemplate.update("UPDATE comercial SET nombre = ? WHERE id = ?"
                    , comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());

        String consola = rows > 0 ? "Update de comercial con " + rows + " registros actualizados" : "No se han realizado updates de comercial";
        System.out.println(consola);
    }

    @Override
    public void delete(int id){
        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        String consola = rows > 0 ? "Delete de comercial con " + rows + " registros actualizados" : "No se han realizado deletes de comercial";
        System.out.println(consola);

    }


}
