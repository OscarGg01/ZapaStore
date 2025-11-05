package com.zapastore.zapastore.model.producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Optional<Producto> findByNombre(String nombre);
    Producto save(Producto producto);
    Producto update(Integer id, Producto productoDetails);
    void deleteById(Integer id);
    List<Producto> findUltimoProductoPorCategoria(); // NUEVO
}
