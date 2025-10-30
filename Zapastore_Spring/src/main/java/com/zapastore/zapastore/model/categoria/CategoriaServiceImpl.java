package com.zapastore.zapastore.model.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la lógica de negocio para Categorías.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    // =========================================================
    // CAMBIO CLAVE: Inyectamos CategoriaDAO
    // =========================================================
    private final CategoriaDAO categoriaDAO; 

    @Autowired
    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    // =========================================================
    // R (Read)
    // =========================================================

    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll(); // Usamos DAO
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaDAO.findById(id); // Usamos DAO
    }

    // =========================================================
    // C (Create)
    // =========================================================

    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getEstado() == null || categoria.getEstado().isEmpty()) {
            categoria.setEstado("Activo");
        }
        return categoriaDAO.save(categoria); // Usamos DAO
    }

    // =========================================================
    // U (Update)
    // =========================================================
    
    // Método para actualizar la categoría (usado por el PUT del Controller)
    public Categoria update(Integer id, Categoria categoriaDetails) {
        return categoriaDAO.findById(id) // Usamos DAO
            .map(categoriaExistente -> {
                if (categoriaDetails.getNombre() != null) {
                    categoriaExistente.setNombre(categoriaDetails.getNombre());
                }
                if (categoriaDetails.getEstado() != null) {
                    categoriaExistente.setEstado(categoriaDetails.getEstado());
                }
                return categoriaDAO.save(categoriaExistente); // Usamos DAO
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Categoría con ID " + id + " no encontrada"
            ));
    }

    // =========================================================
    // D (Soft Delete - Desactivación)
    // =========================================================
    
    @Override
    public void deleteById(Integer id) {
        categoriaDAO.findById(id) // Usamos DAO
            .ifPresentOrElse(categoria -> {
                categoria.setEstado("Inactivo");
                categoriaDAO.save(categoria); // Usamos DAO
            }, () -> {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Categoría con ID " + id + " no encontrada para desactivar"
                );
            });
    }
}