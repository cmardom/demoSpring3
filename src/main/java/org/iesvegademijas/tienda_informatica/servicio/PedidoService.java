package org.iesvegademijas.tienda_informatica.servicio;

import org.iesvegademijas.tienda_informatica.dao.PedidoDAO;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pedido> listAll(){
        return pedidoDAO.getAll();
    }

    public Pedido one (Integer id){
        Optional<Pedido> optPed = pedidoDAO.find(id);

        if(optPed.isPresent()){
            return optPed.get();
        } else {
            return null;
        }
    }

    public void newPedido(Pedido pedido){ pedidoDAO.create(pedido);}
    public void replacePedido(Pedido pedido){ pedidoDAO.update(pedido);}
    public void deletePedido (int id) {pedidoDAO.delete(id);}
}
