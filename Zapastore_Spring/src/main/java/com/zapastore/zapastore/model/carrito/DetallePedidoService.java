package com.zapastore.zapastore.model.carrito;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listarTodo();
    DetallePedido agregar(DetallePedido detalle);
    void eliminar(Integer id);
    Double calcularTotal();
}