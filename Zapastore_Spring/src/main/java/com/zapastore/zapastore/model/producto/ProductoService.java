package com.zapastore.zapastore.model.producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Optional<Producto> findByNombre(String nombre);
    
    // 🔍 MÉTODO AGREGADO PARA LA BÚSQUEDA
    List<Producto> findByNombreContaining(String query); 
    
    Producto save(Producto producto);
    Producto update(Integer id, Producto productoDetails);
    void deleteById(Integer id);
    
    List<Producto> findUltimoProductoPorCategoria(); 
    List<Producto> findByCategoria(Integer categoriaId);
}