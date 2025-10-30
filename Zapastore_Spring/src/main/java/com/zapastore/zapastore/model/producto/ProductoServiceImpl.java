package com.zapastore.zapastore.model.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO productoDAO; 

    @Autowired
    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // =========================================================
    // R (Read)
    // =========================================================

    @Override
    public List<Producto> findAll() {
        return productoDAO.findAll(); 
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoDAO.findById(id); 
    }
    
    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return productoDAO.findByNombre(nombre); 
    }

    // =========================================================
    // C y U (Create y Update)
    // =========================================================

    @Override
    public Producto save(Producto producto) {
        return productoDAO.save(producto); 
    }
    
    @Override
    public Producto update(Integer id, Producto productoDetails) {
        return productoDAO.findById(id) 
            .map(productoExistente -> {
                // Actualiza campos
                if (productoDetails.getNombre() != null) productoExistente.setNombre(productoDetails.getNombre());
                if (productoDetails.getDescripcion() != null) productoExistente.setDescripcion(productoDetails.getDescripcion());
                if (productoDetails.getPrecio() != null) productoExistente.setPrecio(productoDetails.getPrecio());
                // *** ELIMINADO: if (productoDetails.getStock() != null) productoExistente.setStock(productoDetails.getStock()); ***
                if (productoDetails.getCategoriaID() != null) productoExistente.setCategoriaID(productoDetails.getCategoriaID());
                
                return productoDAO.save(productoExistente); 
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado"
            ));
    }


    // =========================================================
    // D (Hard Delete - Eliminación Física)
    // =========================================================
    
    @Override
    public void deleteById(Integer id) {
        productoDAO.findById(id) 
            .ifPresentOrElse(producto -> {
                productoDAO.deleteById(id); 
            }, () -> {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado para eliminación"
                );
            });
    }
}