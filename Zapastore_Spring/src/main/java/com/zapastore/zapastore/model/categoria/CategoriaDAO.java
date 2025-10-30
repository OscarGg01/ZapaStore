package com.zapastore.zapastore.model.categoria;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz Data Access Object (DAO) que define el contrato de acceso a datos para Categoria.
 * El Service inyectará esta interfaz.
 */
public interface CategoriaDAO {

    // Contrato CRUD básico
    List<Categoria> findAll();
    Optional<Categoria> findById(Integer id);
    Categoria save(Categoria categoria);
    
    // Contrato para eliminación/desactivación
    void deleteById(Integer id); 
}