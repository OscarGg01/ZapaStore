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

    @Override
    public Producto save(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Producto update(Integer id, Producto productoDetails) {
        return productoDAO.findById(id)
                .map(prod -> {
                    if (productoDetails.getNombre() != null) prod.setNombre(productoDetails.getNombre());
                    if (productoDetails.getDescripcion() != null) prod.setDescripcion(productoDetails.getDescripcion());
                    if (productoDetails.getPrecio() != null) prod.setPrecio(productoDetails.getPrecio());
                    if (productoDetails.getCategoriaID() != null) prod.setCategoriaID(productoDetails.getCategoriaID());
                    return productoDAO.save(prod);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto con ID " + id + " no encontrado"));
    }

    @Override
    public void deleteById(Integer id) {
        Producto producto = productoDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto con ID " + id + " no encontrado para eliminar"));
        productoDAO.deleteById(producto.getId());
    }

    @Override
    public List<Producto> findUltimoProductoPorCategoria() {
        return productoDAO.findUltimoProductoPorCategoria();
    }

    @Override
    public List<Producto> findByCategoria(Integer categoriaId) {
        return productoDAO.findByCategoria(categoriaId);
    }
}
