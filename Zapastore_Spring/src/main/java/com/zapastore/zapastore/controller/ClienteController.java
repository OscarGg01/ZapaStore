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
public class ClienteController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ClienteController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/cliente/home")
    public String home(Model model) {

        List<Producto> productosPorCategoria = productoService.findUltimoProductoPorCategoria();

        productosPorCategoria.forEach(p -> {
            categoriaService.findById(p.getCategoriaID())
                    .ifPresent(c -> p.setCategoriaNombre(c.getNombre()));
        });

        model.addAttribute("productosDestacados", productosPorCategoria);

        return "cliente/homecliente"; // Reutilizamos tu home.jsp existente
    }

    @GetMapping("/cliente/contacto")
    public String contacto() {
        return "cliente/contactocliente"; // Spring buscará WEB-INF/jsp/cliente_interfaz/contacto.jsp
    }

    @GetMapping("/cliente/ofertas")
    public String ofertas() {
        return "cliente/ofertascliente";
    }
}
