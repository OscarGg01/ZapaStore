package com.zapastore.zapastore.model.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    // 💡 NOTA: Asumo que ProductoDAO es la interfaz que hereda de JpaRepository
    // y tiene acceso a los métodos de Spring Data JPA, como findByNombreContainingIgnoreCase.
    private final ProductoRepository productoRepository; // Cambiamos el tipo a ProductoRepository para consistencia con el método de búsqueda

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // --- MÉTODOS DE BÚSQUEDA ---
    
    /**
     * Implementación del método de búsqueda para el controlador.
     * Llama al método de Spring Data JPA que filtra por nombre.
     */
    @Override
    public List<Producto> findByNombreContaining(String query) {
        // Usamos el método definido por convención en ProductoRepository.
        // Asumiendo que productoDAO/productoRepository lo implementa.
        return productoRepository.findByNombreContainingIgnoreCase(query);
    }
    
    // --- MÉTODOS CRUD BÁSICOS ---

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        // Necesitarías implementar findByNombre en el repositorio si se usa.
        // Por ahora, solo devolveremos el primer resultado de la búsqueda si existe.
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream().findFirst();
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Integer id, Producto productoDetails) {
        return productoRepository.findById(id)
                .map(prod -> {
                    if (productoDetails.getNombre() != null) prod.setNombre(productoDetails.getNombre());
                    if (productoDetails.getDescripcion() != null) prod.setDescripcion(productoDetails.getDescripcion());
                    if (productoDetails.getPrecio() != null) prod.setPrecio(productoDetails.getPrecio());
                    // Permite actualizar el ID de la categoría
                    if (productoDetails.getCategoriaID() != null) prod.setCategoriaID(productoDetails.getCategoriaID());
                    // Si tienes imagen_url, actualízala también
                    if (productoDetails.getImagen_url() != null) prod.setImagen_url(productoDetails.getImagen_url());
                    return productoRepository.save(prod);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto con ID " + id + " no encontrado"));
    }

    @Override
    public void deleteById(Integer id) {
        // Mejorar la eliminación para evitar el fetch si solo queremos saber si existe antes de eliminar.
        if (!productoRepository.existsById(id)) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto con ID " + id + " no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }

    // --- MÉTODOS PERSONALIZADOS ---
    
    @Override
    public List<Producto> findUltimoProductoPorCategoria() {
        return productoRepository.findUltimoProductoPorCategoria();
    }

    @Override
    public List<Producto> findByCategoria(Integer categoriaId) {
        return productoRepository.findByCategoria(categoriaId);
    }
}