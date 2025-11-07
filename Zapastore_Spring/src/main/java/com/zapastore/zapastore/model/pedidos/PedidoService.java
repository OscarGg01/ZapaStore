package com.zapastore.zapastore.model.pedidos;

import com.zapastore.zapastore.model.usuario.Usuario;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido save(Pedido pedido);
    List<Pedido> findByCliente(Usuario cliente);
    Optional<Pedido> findById(Integer id);
    void deleteById(Integer id);

    // Nuevo método para pedidos pendientes
    List<Pedido> findByClienteAndEstado(Usuario cliente, String estado);
}
