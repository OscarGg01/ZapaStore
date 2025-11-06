package com.zapastore.zapastore.model.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>, ProductoDAO {

    // 🔍 MÉTODO AGREGADO PARA LA BÚSQUEDA
    List<Producto> findByNombreContainingIgnoreCase(String nombre); 
    
    @Query("SELECT p FROM Producto p WHERE p.id IN " +
            "(SELECT MAX(p2.id) FROM Producto p2 GROUP BY p2.categoriaID)")
    List<Producto> findUltimoProductoPorCategoria();

    @Query("SELECT p FROM Producto p WHERE p.categoriaID = :categoriaId")
    List<Producto> findByCategoria(@Param("categoriaId") Integer categoriaId);
}