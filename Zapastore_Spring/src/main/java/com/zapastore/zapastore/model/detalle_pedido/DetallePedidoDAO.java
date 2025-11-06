package com.zapastore.zapastore.model.detalle_pedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoDAO {

    List<DetallePedido> findAll();
    Optional<DetallePedido> findById(Long id);
    DetallePedido save(DetallePedido detallePedido);
    void deleteById(Long id);
    List<DetallePedido> findByPedidoId(Long pedidoId);
}
