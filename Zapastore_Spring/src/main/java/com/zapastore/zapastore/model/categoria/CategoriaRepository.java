package com.zapastore.zapastore.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para operaciones CRUD en la entidad Categoria.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Métodos CRUD básicos listos para usar
}