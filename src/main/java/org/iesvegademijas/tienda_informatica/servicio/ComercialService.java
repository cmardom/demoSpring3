package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAOImpl;
import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
import org.iesvegademijas.tienda_informatica.dto.ClienteDTO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ClienteDAO clienteDAO;



    public List<Pedido> mostrarPedidosComercial(int id_comercial) {
        return pedidoDAO.mostrarPedidosComercial(id_comercial);

    }

    public List<Pedido> mostrarPedidosCliente (int id_cliente){
        return pedidoDAO.mostrarPedidosCliente(id_cliente);
    }

   /* public List<Cliente> clientesYTotalDelComercialOrdenados (int id_cliente){
        List<Cliente> clientes = clienteDAO.getAll();
        List<Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        //double resultado = pedidos.stream().map(Pedido::getTotal).reduce(0.0, Double::sum);


        // cuantía total de los pedidos para cada cliente
        List<Cliente> clientesConTotal = clientes.stream()
                .map(cliente -> {
                    double totalPedidos = pedidos.stream()
                            .filter(pedido -> pedido.getId_cliente() == cliente.getId())
                            .mapToDouble(Pedido::getTotal)
                            .sum();
                    cliente.setCuantiaTotalPedidos(totalPedidos);
                    return cliente;
                })
                .collect(Collectors.toList());

        // lista de clientes por cuantía total de pedidos (ascendente)
        List<Cliente> clientesOrdenados = clientesConTotal.stream()
                .sorted(Comparator.comparingDouble(Cliente::getCuantiaTotalPedidos))
                .collect(Collectors.toList());

        return clientesOrdenados;

    }*/

    public ClienteDTO totalPedidoCliente (int id_cliente){
        List<Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        List<Pedido> pedidosComercial = new ArrayList<>();

        for(int i = 0; i < pedidos.size(); i++){
            pedidosComercial = mostrarPedidosComercial(pedidos.get(i).getId_comercial());


        }


        double resultado = pedidosComercial.stream().map(Pedido::getTotal).reduce(0.0, Double::sum);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(oneCli(id_cliente).getId());
        clienteDTO.setNombre(oneCli(id_cliente).getNombre());
        clienteDTO.setApellido1(oneCli(id_cliente).getApellido1());
        clienteDTO.setApellido2(oneCli(id_cliente).getApellido2());
        clienteDTO.setCiudad(oneCli(id_cliente).getCiudad());
        clienteDTO.setCategoria(oneCli(id_cliente).getCategoria());
        clienteDTO.setTotalPedido(resultado);
        return clienteDTO;

    }

    public double totalPedidoComercial (int id_comercial){
        List<Pedido> pedidos = mostrarPedidosComercial(id_comercial);

        double resultado = pedidos.stream().map(p -> p.getTotal()).reduce(0.0, Double::sum).doubleValue();
        return resultado;
    }

    public double mediaPedidoComercial (int id_comercial){
        List<Pedido> pedidos = mostrarPedidosComercial(id_comercial);
        int numPedidos = pedidos.size();
        double total = totalPedidoComercial(id_comercial);

        return total / numPedidos;

    }


    public List<Pedido> pedidosOrdenadosAscendente (int id_comercial){
        List<Pedido> pedidos = mostrarPedidosComercial(id_comercial);
        List<Pedido> pedidosOrdenados = pedidos.stream().sorted(Comparator.comparing(Pedido::getTotal)).collect(Collectors.toList());
        return pedidosOrdenados;
    }

    public Pedido pedidoMaximo (int id_comercial){
        List<Pedido> pedidosOrdenados = pedidosOrdenadosAscendente(id_comercial);
        if (!pedidosOrdenados.isEmpty()) {
            Pedido pedidoMax = pedidosOrdenados.get(pedidosOrdenados.size()-1);
            return pedidoMax;

        } else {
            return null;
        }

    }

    public Pedido pedidoMinimo (int id_comercial){
        List<Pedido> pedidosOrdenados = pedidosOrdenadosAscendente(id_comercial);
        if (!pedidosOrdenados.isEmpty()){
            Pedido pedidoMin = pedidosOrdenados.get(0);
            return pedidoMin;
        } else {
            return null;
        }

    }


    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }
    public List<Cliente> listAllCli(){
        return clienteDAO.getAll();
    }

    public Comercial one (Integer id){
        Optional<Comercial> optCom = comercialDAO.find(id);

        if (optCom.isPresent()){
            return optCom.get();
        } else {
            return null;
        }
    }

    public Cliente oneCli (Integer id){
        Optional<Cliente> optCom = clienteDAO.find(id);

        if (optCom.isPresent()){
            return optCom.get();
        } else {
            return null;
        }
    }

    public void newComercial(Comercial comercial){
        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial){
        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id){
        comercialDAO.delete(id);
    }







}
