package com.zapastore.zapastore.model.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementación del acceso a datos. 
 * Extiende JpaRepository para obtener la implementación automática 
 * y extiende ProductoDAO para cumplir el contrato.
 */
@Repository
// Extiende JpaRepository (funcionalidad) y ProductoDAO (contrato)
public interface ProductoRepository extends JpaRepository<Producto, Integer>, ProductoDAO {
    
    // Este método ya está en ProductoDAO, pero JpaRepository lo implementa.
    // Lo dejamos aquí si necesitamos alguna Query especial personalizada que JpaRepository no cubre
    // Optional<Producto> findByNombre(String nombre); 
}