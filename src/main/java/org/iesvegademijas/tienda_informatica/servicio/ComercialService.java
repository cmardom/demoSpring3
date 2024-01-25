package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAOImpl;
import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
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
