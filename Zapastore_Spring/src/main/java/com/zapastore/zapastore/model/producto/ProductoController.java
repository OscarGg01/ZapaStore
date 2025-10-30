package com.zapastore.zapastore.model.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET /api/v1/admin/productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll(); 
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // GET /api/v1/admin/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.findById(id);

        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /api/v1/admin/productos/buscar?nombre=X
    @GetMapping("/buscar")
    public ResponseEntity<Producto> getProductoByNombre(@RequestParam String nombre) {
        Optional<Producto> producto = productoService.findByNombre(nombre);

        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /api/v1/admin/productos
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        producto.setId(null); 
        Producto nuevoProducto = productoService.save(producto); 
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // PUT /api/v1/admin/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        try {
            Producto productoActualizado = productoService.update(id, productoDetails);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    // DELETE /api/v1/admin/productos/{id} (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable Integer id) {
        try {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}