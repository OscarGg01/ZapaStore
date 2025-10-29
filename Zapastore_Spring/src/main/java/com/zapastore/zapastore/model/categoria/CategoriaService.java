package com.zapastore.zapastore.model.categoria;

import java.util.List;
import java.util.Optional;

/**
 * Define las operaciones de negocio para las Categorías.
 */
public interface CategoriaService {

    // Obtener todas las categorías
    List<Categoria> findAll();

    // Obtener una categoría por ID
    Optional<Categoria> findById(Integer id);

    // Guardar o actualizar una categoría
    Categoria save(Categoria categoria);

    // Eliminar una categoría por ID
    void deleteById(Integer id);
}