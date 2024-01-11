package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;

import java.util.Optional;
import java.util.List;

public interface ComercialDAO {

    public void create (Comercial comercial);
    public List<Comercial> getAll();
    public Optional<Comercial> find(int id);
    public void update (Comercial comercial);
    public void delete (int id);
}
