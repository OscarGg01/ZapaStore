package com.zapastore.zapastore.model.pedidos;

import com.zapastore.zapastore.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Usuario cliente);
}
