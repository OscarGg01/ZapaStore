package com.zapastore.zapastore.model.pedidos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zapastore.zapastore.model.usuario.Usuario;

public interface PedidoDAO extends JpaRepository<Pedido, Integer> {
    // Antes: List<Pedido> findByUsuarioId(Integer id);
    List<Pedido> findByCliente(Usuario cliente);
}
