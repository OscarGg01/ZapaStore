package com.zapastore.zapastore.model.pedidos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zapastore.zapastore.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Usuario cliente);

    // Ventas por día (sin importar la hora)
    @Query("SELECT p FROM Pedido p WHERE FUNCTION('DATE', p.fecha) = FUNCTION('DATE', :fecha)")
    List<Pedido> findByFecha(@Param("fecha") Date fecha);

    // Ventas por rango de fechas
    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :inicio AND :fin")
    List<Pedido> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    // Nuevo método para obtener pedidos pendientes
    List<Pedido> findByClienteAndEstado(Usuario cliente, String estado);
}
