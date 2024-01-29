package org.iesvegademijas.tienda_informatica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;


@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteDTO extends Cliente{

   private double totalPedido;

   public ClienteDTO (int id, String nombre, String apellido1, String apellido2, String ciudad, int categoria, String email, double totalPedido){
      super(id, nombre, apellido1, apellido2, ciudad, categoria, email);

      this.totalPedido = totalPedido;
   }

   public ClienteDTO() {

   }

   public double getTotalPedido() {
      return totalPedido;
   }

   public void setTotalPedido(double totalPedido) {
      this.totalPedido = totalPedido;
   }


}
