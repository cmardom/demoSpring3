package org.iesvegademijas.tienda_informatica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;


@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteDTO extends Cliente{

   private double totalPedido;

   public ClienteDTO (int id, String nombre, String apellido1, String apellido2, String ciudad, int categoria, double totalPedido){
      super(id, nombre, apellido1, apellido2, ciudad, categoria);

      this.totalPedido = totalPedido;
   }

   public ClienteDTO() {

   }


   //public ClienteDTO(Cliente cliente, double totalPedidoCliente);
}
