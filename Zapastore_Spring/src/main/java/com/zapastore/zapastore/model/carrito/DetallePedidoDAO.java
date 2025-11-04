package com.zapastore.zapastore.model.carrito;

import java.util.List;

public interface DetallePedidoDAO {

    // Buscar todos los detalles por ID de pedido
    List<DetallePedido> findByPedidoId(Long pedidoId);

    // Guardar un nuevo detalle o actualizar uno existente
    DetallePedido save(DetallePedido detalle);

    // Eliminar detalle por ID
    void deleteById(Long id);
}
