package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.categoria.Categoria;
import com.zapastore.zapastore.model.categoria.CategoriaService;
import com.zapastore.zapastore.model.producto.Producto;
import com.zapastore.zapastore.model.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public CatalogoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/catalogo")
    public String catalogo(@RequestParam(value = "categoriaId", required = false) Integer categoriaId,
                           Model model) {

        // Obtener todas las categorías para el select
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);

        // Obtener productos: todos o filtrados por categoría
        List<Producto> productos;
        if (categoriaId != null) {
            productos = productoService.findByCategoria(categoriaId);
        } else {
            productos = productoService.findAll();
        }

        // Asignar nombre de categoría a cada producto
        productos.forEach(p -> categoriaService.findById(p.getCategoriaID())
                .ifPresent(c -> p.setCategoriaNombre(c.getNombre()))
        );

        model.addAttribute("productos", productos);

        return "user/catalogo";
    }
}
