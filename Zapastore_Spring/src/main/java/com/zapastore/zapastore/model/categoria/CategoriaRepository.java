package com.zapastore.zapastore.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementación del acceso a datos. 
 * Extiende JpaRepository para obtener la implementación automática 
 * y extiende CategoriaDAO para cumplir el contrato (Arquitectura Hexagonal).
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>, CategoriaDAO {
    // No necesitamos código aquí. JpaRepository implementa automáticamente los métodos.
}