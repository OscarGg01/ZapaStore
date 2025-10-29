package com.zapastore.zapastore.model.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la lógica de negocio para Categorías.
 * Provee métodos para el CRUD y maneja la desactivación como "Soft Delete".
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // =========================================================
    // R (Read)
    // =========================================================

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    // =========================================================
    // C (Create)
    // =========================================================

    @Override
    public Categoria save(Categoria categoria) {
        // Asegurar que el estado inicial sea "Activo" si no se especifica
        if (categoria.getEstado() == null || categoria.getEstado().isEmpty()) {
            categoria.setEstado("Activo");
        }
        return categoriaRepository.save(categoria);
    }

    // =========================================================
    // U (Update)
    // =========================================================
    
    // Método para actualizar la categoría (usado por el PUT del Controller)
    public Categoria update(Integer id, Categoria categoriaDetails) {
        return categoriaRepository.findById(id)
            .map(categoriaExistente -> {
                // Actualiza solo los campos que vienen en el body
                if (categoriaDetails.getNombre() != null) {
                    categoriaExistente.setNombre(categoriaDetails.getNombre());
                }
                if (categoriaDetails.getEstado() != null) {
                    categoriaExistente.setEstado(categoriaDetails.getEstado());
                }
                return categoriaRepository.save(categoriaExistente);
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Categoría con ID " + id + " no encontrada"
            ));
    }

    // =========================================================
    // D (Soft Delete - Desactivación)
    // =========================================================
    
    /**
     * Reemplaza la eliminación física. Cambia el estado de la categoría a "Inactivo".
     * @param id El ID de la categoría a desactivar.
     */
    @Override
    public void deleteById(Integer id) {
        categoriaRepository.findById(id)
            .ifPresentOrElse(categoria -> {
                // Si la categoría existe, la marcamos como Inactiva
                categoria.setEstado("Inactivo");
                categoriaRepository.save(categoria);
                // NOTA: Para realizar una eliminación física, se usaría: categoriaRepository.delete(categoria);
            }, () -> {
                // Si no existe, podemos lanzar una excepción (aunque para DELETE, 204 No Content es común)
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Categoría con ID " + id + " no encontrada para desactivar"
                );
            });
    }
}