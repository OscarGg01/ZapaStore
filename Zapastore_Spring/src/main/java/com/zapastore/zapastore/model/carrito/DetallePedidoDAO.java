package com.zapastore.zapastore.model.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DetallePedidoDAO {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido guardar(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}
