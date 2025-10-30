package com.zapastore.zapastore.model.categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Integer id);

    Categoria save(Categoria categoria);
    
    // Método para la actualización completa (usado en el PUT del service)
    Categoria update(Integer id, Categoria categoriaDetails);

    // Este método ya no hace DELETE físico, sino una DESACTIVACIÓN (Soft Delete)
    void deleteById(Integer id); 
}