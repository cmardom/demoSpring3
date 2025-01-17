package org.iesvegademijas.tienda_informatica.controlador;

import jakarta.validation.Valid;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String listarClientes (Model model){
        List<Cliente> listAllCli = clienteService.listAll();
        model.addAttribute("listaClientes", listAllCli);
        return "clientes";
    }

    @GetMapping("/clientes/{id}")
    public String detalleCliente(Model model, @PathVariable Integer id) {
        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        /*para mostrar los pedidos de cliente*/
        int idCliente = cliente.getId();
        List<Pedido> pedidos = clienteService.mostrarPedidosCliente(id);
        List<Pedido> pedidosFiltradosCliente = pedidos.stream().filter(pedido -> pedido.getId_cliente() == idCliente).collect(Collectors.toList());

        if(pedidosFiltradosCliente.isEmpty()){
            pedidosFiltradosCliente = null;
        }
        model.addAttribute("pedidosFiltradosCliente", pedidosFiltradosCliente);


        Comercial comercial = clienteService.oneCom(id);
        /*listar pedidos de comercial*/
        List<Pedido> pedidosDelComercial = clienteService.mostrarPedidosComercial(id);
        //int idComercial = comercial.getId();
        List<Pedido> pedidosDelClienteConComercial = pedidosDelComercial.stream().filter(pedido -> pedido.getId_comercial() == idCliente).collect(Collectors.toList());

        List<Integer> idsQueSalenEnLaListaDePedidos = pedidosDelClienteConComercial.stream().map(Pedido::getId_cliente).collect(Collectors.toList());

        model.addAttribute("pedidosDelClienteConComercial", pedidosDelClienteConComercial);
        model.addAttribute("idsQueSalenEnLasListaDePedidos", idsQueSalenEnLaListaDePedidos);


        return "detalle-cliente";

    }


    @GetMapping("/clientes/crear")
    public String crear(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "crear-cliente";
    }

    @PostMapping("/clientes/crear")
    public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("cliente", cliente);
            //redireccion interna
            return "crear-cliente";
        }
        clienteService.newCliente(cliente);


        return "redirect:/clientes" ;

    }

    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id){
        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);
        return "editar-cliente";
    }

    @PostMapping("/clientes/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "editar-cliente";
        }
        clienteService.replaceCliente(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id){
        clienteService.deletecliente(id);
        return new RedirectView("/clientes");
    }
}
