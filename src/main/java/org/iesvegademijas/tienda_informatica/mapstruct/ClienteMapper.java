package org.iesvegademijas.tienda_informatica.mapstruct;

import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", source="cliente.id")
    @Mapping(target = "nombre", source = "cliente.nombre")
    @Mapping(target = "apellido1", source = "cliente.apellido1")
    @Mapping(target = "apellido2", source = "cliente.apellido2")
    @Mapping(target = "ciudad", source = "ciudad")
    ClienteDTO clienteAClienteDTO (Cliente cliente);

}
