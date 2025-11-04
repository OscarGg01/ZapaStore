package com.zapastore.zapastore.model.carrito;

import org.springframework.lang.NonNull; // Importación clave
import java.util.List;

public interface DetallePedidoService {
    
    // 1. El pedidoId no debe ser nulo para buscar los detalles.
    // 2. La lista devuelta debe ser una lista vacía, no nula.
    List<DetallePedido> listarPorPedido(@NonNull Long pedidoId);
    
    // 3. El DetallePedido a guardar no debe ser nulo.
    // 4. El método debe devolver el objeto guardado (no nulo).
    @NonNull 
    DetallePedido guardar(@NonNull DetallePedido detalle);
    
    // 5. El ID a eliminar no debe ser nulo.
    void eliminar(@NonNull Long id);
}