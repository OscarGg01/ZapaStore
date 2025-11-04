package com.zapastore.zapastore.model.carrito;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listarPorPedido(Long pedidoId);
    DetallePedido guardar(DetallePedido detalle);
    void eliminar(Long id);
}
