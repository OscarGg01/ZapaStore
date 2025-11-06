package com.zapastore.zapastore.model.producto;

import java.util.List;
import java.util.Optional;

public interface ProductoDAO {

    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Optional<Producto> findByNombre(String nombre);
    
    // 🔍 MÉTODO AGREGADO PARA LA BÚSQUEDA POR NOMBRE (Ignorando mayúsculas/minúsculas)
    // Este método debe ser implementado por ProductoRepository/ProductoServiceImpl.
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    Producto save(Producto producto);
    void deleteById(Integer id);
    
    List<Producto> findUltimoProductoPorCategoria(); // NUEVO
    List<Producto> findByCategoria(Integer categoriaId);
}