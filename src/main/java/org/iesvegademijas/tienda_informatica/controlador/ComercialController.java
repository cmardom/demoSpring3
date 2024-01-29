package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.mapstruct.ClienteMapper;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ClienteMapper clienteMapper;


    @GetMapping("/comerciales")
    public String listarComerciales (Model model){
        List<Comercial> listAllCom = comercialService.listAll();
        model.addAttribute("listaComerciales", listAllCom);
        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalleComercial(Model model, @PathVariable Integer id) {
        Comercial comercial = comercialService.one(id);


        /*listar pedidos de comercial*/
        List<Pedido> pedidos = comercialService.mostrarPedidosComercial(id);
        int idComercial = comercial.getId();
        List<Pedido> pedidosFiltradosComercial = pedidos.stream().filter(pedido -> pedido.getId_comercial() == idComercial).collect(Collectors.toList());

        List<Integer> idsQueSalenEnLaListaDePedidos = pedidosFiltradosComercial.stream().map(Pedido::getId_cliente).collect(Collectors.toList());

        List<Cliente> listaCli = comercialService.listAllCli();
        List<ClienteDTO> clientesConTotales = new ArrayList<>();
        List<ClienteDTO> clientesConTotalesOrdenados = new ArrayList<>();


        //para poder mostrar un mensaje si no hay pedidos

/*        if (pedidosFiltradosComercial.isEmpty()) {
            pedidosFiltradosComercial = null;
        } else {*/
            /*recorrer la lista que enlazara con los clientes de los pedidos de ese comercial*/
           /* for (int i = 0; i < idsQueSalenEnLaListaDePedidos.size(); i++) {
                if (idsQueSalenEnLaListaDePedidos.get(i) == listaCli.get(i).getId()) {
                    Cliente clienteDelPedido = comercialService.oneCli(idsQueSalenEnLaListaDePedidos.get(i));
                    model.addAttribute("clienteDelPedido", clienteDelPedido);

                    clientesConTotales= comercialService.totalPedidoCliente(clienteDelPedido.getId());
                    //clientesConTotales.add(comercialService.totalPedidoCliente(clienteDelPedido.getId()));
                    //clientesConTotales.add(clienteConTotal);








                }
            }*/
/*

            clientesConTotalesOrdenados = clientesConTotales.stream()
                    .sorted(Comparator.comparingDouble(ClienteDTO::getTotalPedido))
                    .collect(Collectors.toList());

            model.addAttribute("clientesConTotalesOrdenados", clientesConTotalesOrdenados);

*/

            //}
            model.addAttribute("pedidosFiltradosComercial", pedidosFiltradosComercial);

            List<ClienteDTO> clientesConTotales2 = new ArrayList<>();
            for (int i = 0; i < pedidosFiltradosComercial.size(); i++){
                if (pedidosFiltradosComercial.get(i).getId_cliente() == listaCli.get(i).getId()){
                    ClienteDTO clienteConTotal = comercialService.totalPedidoCliente(pedidosFiltradosComercial.get(i).getId_cliente());

                    if (!clientesConTotales2.contains(clienteConTotal)){
                        clientesConTotales2.add(clienteConTotal);

                        List<ClienteDTO> clientesConTotalesOrdenados2 = clientesConTotales2.stream()
                                .sorted(Comparator.comparingDouble(ClienteDTO::getTotalPedido))
                                .collect(Collectors.toList());

                        model.addAttribute("clientesConTotalesOrdenados2", clientesConTotalesOrdenados2);

                    }
                }
            }


            double total = comercialService.totalPedidoComercial(id);

            model.addAttribute("totalPedidosComercial", total);

            double media = comercialService.mediaPedidoComercial(id);
            model.addAttribute("mediaPedidosComercial", media);

            Pedido pedidoMax = comercialService.pedidoMaximo(id);
            model.addAttribute("pedidoMaximo", pedidoMax);

            Pedido pedidoMin = comercialService.pedidoMinimo(id);
            model.addAttribute("pedidoMinimo", pedidoMin);

        /*List<Cliente> clientesOrdenados = comercialService.clientesYTotalDelComercialOrdenados(clienteDelPedido.getId());
        model.addAttribute("clientesDelComercialOrdenados", clientesOrdenados);*/


            //ClienteDTO clienteConTotal = comercialService.totalPedidoCliente(listaCli.get(0).getId(), comercial.getId());
            //model.addAttribute("clienteConTotal", clienteConTotal);

            model.addAttribute("comercial", comercial);
            return "detalle-comercial";
        }

    @GetMapping("/comerciales/crear")
    public String crear(Model model){
        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);
        return "crear-comercial";
    }

    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);

        return new RedirectView("/comerciales") ;

    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id){
        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);
        return "editar-comercial";
    }

    @PostMapping("/comerciales/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial){
        comercialService.replaceComercial(comercial);
        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id){
        comercialService.deleteComercial(id);
        return new RedirectView("/comerciales");
    }


}
