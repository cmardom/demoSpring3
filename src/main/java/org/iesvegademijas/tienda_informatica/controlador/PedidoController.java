package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.iesvegademijas.tienda_informatica.servicio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/pedidos")
    public String listarPedidos (Model model){
        List<Pedido> listAllPe = pedidoService.listAll();
        model.addAttribute("listaPedidos", listAllPe);
        int[] IDsDeClientesEnPedidos = new int[listAllPe.size()];
      // List<Pedido> pedidosClientes = clienteService.mostrarPedidosCliente()

       /* for (int i = 0; i < listAllPe.size(); i++) {
            IDsDeClientesEnPedidos[i] = listAllPe.get(i).getId_cliente();
            pedidosClientes.set(i,clienteService.pedidosOrdenadosAscendente(IDsDeClientesEnPedidos[i]));

        }*/

        //1. Obtener listado de clientes
            //array de IDs de clientes
       /* for (int i = 0; i < listAllPe.size(); i++) {
            IDsDeClientesEnPedidos[i] = listAllPe.get(i).getId_cliente();}

        List<Cliente> clientes = clienteService.pedidosOrdenadosAscendente()*/

        //List<Cliente> clientes = clienteService.

        //2. Ordenar listado de clientes


        return "pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String detallePedido(Model model, @PathVariable Integer id){
        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);
        return "detalle-pedido";
    }

    @GetMapping("/pedidos/crear")
    public String crear (Model model){
        Pedido pedido = new Pedido();
        model.addAttribute("pedido", pedido);
        return "crear-pedido";
    }

    @PostMapping("/pedidos/crear")
    public RedirectView submitCrear (@ModelAttribute("pedido") Pedido pedido){
        pedidoService.newPedido(pedido);
        return new RedirectView("/pedidos");
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id){
        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);
        return "editar-pedido";
    }

    @PostMapping("pedidos/editar/{id}")
    public RedirectView submitEditar (@PathVariable Integer id){
        pedidoService.deletePedido(id);
        return new RedirectView("/pedidos");
    }

}
