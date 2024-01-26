package org.iesvegademijas.tienda_informatica.dto;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface ClienteDTO {

   public ClienteDTO(Cliente cliente, double totalPedidoCliente);
}
