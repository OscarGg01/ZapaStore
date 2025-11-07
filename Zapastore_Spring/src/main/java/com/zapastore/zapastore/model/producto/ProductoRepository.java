package com.zapastore.zapastore.model.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// ¡IMPORTANTE! Eliminamos la herencia de ProductoDAO para evitar la ambigüedad.
public interface ProductoRepository extends JpaRepository<Producto, Integer> { 
    
    // Los métodos CRUD (findById, save, deleteById) se heredan automáticamente de JpaRepository.

    // 🔍 Método personalizado: Búsqueda por nombre (convención de Spring Data)
    List<Producto> findByNombreContainingIgnoreCase(String nombre); 
    
    // ⬇️ Métodos con consulta JPQL personalizada (@Query)
    
    // Último producto por categoría
    @Query("SELECT p FROM Producto p WHERE p.id IN " +
            "(SELECT MAX(p2.id) FROM Producto p2 GROUP BY p2.categoriaID)")
    List<Producto> findUltimoProductoPorCategoria();

    // Productos por categoría
    @Query("SELECT p FROM Producto p WHERE p.categoriaID = :categoriaId")
    List<Producto> findByCategoria(@Param("categoriaId") Integer categoriaId);
}