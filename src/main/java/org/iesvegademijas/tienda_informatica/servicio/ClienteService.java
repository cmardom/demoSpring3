package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ClienteService {
    @Autowired
    private ClienteDAO clienteDAO;

    /*PRUEBA*/
    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ComercialDAO comercialDAO;


    public List<Pedido> mostrarPedidosCliente(int id_cliente) {
        return pedidoDAO.mostrarPedidosCliente(id_cliente);

    }

    public List<Pedido> mostrarPedidosComercial(int id_comercial) {
        return pedidoDAO.mostrarPedidosComercial(id_comercial);

    }

    public ClienteDTO clienteConTotal (int id_cliente){
        List <Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        double resultado = pedidos.stream().map(p->p.getTotal()).reduce(0.0, Double::sum).doubleValue();

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(one(id_cliente).getId());
        clienteDTO.setNombre(one(id_cliente).getNombre());
        clienteDTO.setApellido1(one(id_cliente).getApellido1());
        clienteDTO.setApellido2(one(id_cliente).getApellido2());
        clienteDTO.setCiudad(one(id_cliente).getCiudad());
        clienteDTO.setCategoria(one(id_cliente).getCategoria());
        clienteDTO.setTotalPedido(resultado);
        return clienteDTO;



    }

    public Cliente obtenerClientePorPedido (Pedido pedido){
        int idClienteEnPedido = pedido.getId_cliente();
        return one(idClienteEnPedido);
    }


    public List<Pedido> pedidosOrdenadosAscendenteUnCliente (int id_cliente){
        List<Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        List<Pedido> pedidosOrdenados = pedidos.stream().sorted(Comparator.comparing(Pedido::getTotal)).collect(Collectors.toList());
        return pedidosOrdenados;
    }



    public List<Cliente> listAll(){
        return clienteDAO.getAll();
    }

    public Cliente one (Integer id){
        Optional<Cliente> optCli = clienteDAO.find(id);

        if (optCli.isPresent()){
            return optCli.get();
        } else {
            return null;
        }

    }

    public Comercial oneCom (Integer id2){
        Optional<Comercial> optCli = comercialDAO.find(id2);

        if (optCli.isPresent()){
            return optCli.get();
        } else {
            return null;
        }

    }

    public void newCliente(Cliente cliente){
        clienteDAO.create(cliente);
    }

    public void replaceCliente(Cliente cliente){
        clienteDAO.update(cliente);
    }

    public void deletecliente(int id){
        clienteDAO.delete(id);
    }



}
