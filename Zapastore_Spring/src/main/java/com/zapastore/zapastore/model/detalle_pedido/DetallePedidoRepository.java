package com.zapastore.zapastore.model.detalle_pedido;

import com.zapastore.zapastore.model.pedidos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    // Listar detalles por pedido
    List<DetallePedido> findByPedido(Pedido pedido);
}
