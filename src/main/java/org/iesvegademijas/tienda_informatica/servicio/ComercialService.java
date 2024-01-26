package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAOImpl;
import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
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


    /*PRUEBA*/
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

    public List<Cliente> clientesYTotalDelComercialOrdenados (int id_cliente){
        List<Cliente> clientes = clienteDAO.getAll();
        List<Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        //double resultado = pedidos.stream().map(Pedido::getTotal).reduce(0.0, Double::sum);


        // Calcular la cuantía total de los pedidos para cada cliente
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

        // Ordenar la lista de clientes por la cuantía total de los pedidos de forma ascendente
        List<Cliente> clientesOrdenados = clientesConTotal.stream()
                .sorted(Comparator.comparingDouble(Cliente::getCuantiaTotalPedidos))
                .collect(Collectors.toList());

        return clientesOrdenados;

    }

    public double totalPedidoCliente (int id_cliente){
        List<Pedido> pedidos = mostrarPedidosCliente(id_cliente);
        double resultado = pedidos.stream().map(p -> p.getTotal()).reduce(0.0, Double::sum).doubleValue();
        return resultado;
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
        Pedido pedidoMax = pedidosOrdenados.getLast();
        return pedidoMax;
    }

    public Pedido pedidoMinimo (int id_comercial){
        List<Pedido> pedidosOrdenados = pedidosOrdenadosAscendente(id_comercial);
        Pedido pedidoMin = pedidosOrdenados.getFirst();
        return pedidoMin;
    }


    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }

    public Comercial one (Integer id){
        /*mover aqui la logica de pedido u otro metodo para las listas*/
        Optional<Comercial> optCom = comercialDAO.find(id);

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
