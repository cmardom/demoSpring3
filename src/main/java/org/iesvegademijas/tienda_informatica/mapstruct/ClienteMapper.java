package org.iesvegademijas.tienda_informatica.mapstruct;

import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", source="cliente.id")
    ClienteDTO clienteAClienteDTO (Cliente cliente);

}
