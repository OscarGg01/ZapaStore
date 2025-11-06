package com.zapastore.zapastore.model.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/lista")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "admin/lista_productos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/form_producto";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productoService.findById(id);
        if (productoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado con ID: " + id);
            return "redirect:/admin/productos/lista";
        }
        model.addAttribute("producto", productoOpt.get());
        return "admin/form_producto";
    }

    @PostMapping({"", "/"})
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        productoService.save(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto guardado con éxito!");
        return "redirect:/admin/productos/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto.");
        }
        return "redirect:/admin/productos/lista";
    }
}
