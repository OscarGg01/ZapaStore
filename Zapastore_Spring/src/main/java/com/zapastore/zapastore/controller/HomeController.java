package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.categoria.CategoriaService;
import com.zapastore.zapastore.model.producto.Producto;
import com.zapastore.zapastore.model.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public HomeController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {

        List<Producto> productosPorCategoria = productoService.findUltimoProductoPorCategoria();

        productosPorCategoria.forEach(p -> {
            categoriaService.findById(p.getCategoriaID())
                    .ifPresent(c -> p.setCategoriaNombre(c.getNombre()));
        });

        model.addAttribute("productosDestacados", productosPorCategoria);

        return "cliente_interfaz/home";
    }
}
