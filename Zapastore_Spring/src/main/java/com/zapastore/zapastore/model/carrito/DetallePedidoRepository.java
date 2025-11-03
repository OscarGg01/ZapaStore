package com.zapastore.zapastore.model.carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    // si en el futuro necesitas consultas personalizadas las agregas aquí
}
