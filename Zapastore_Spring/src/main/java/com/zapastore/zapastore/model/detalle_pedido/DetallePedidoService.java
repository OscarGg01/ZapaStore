package com.zapastore.zapastore.model.detalle_pedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoService {

    List<DetallePedido> listarPorPedido(Integer pedidoId);
    Optional<DetallePedido> findById(Integer id);
    DetallePedido save(DetallePedido detallePedido);
    void deleteById(Integer id);
}
