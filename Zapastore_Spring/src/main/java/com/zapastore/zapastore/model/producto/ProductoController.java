package com.zapastore.zapastore.model.producto; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller 
@RequestMapping("/admin/productos") 
public class ProductoController {

    private final ProductoService productoService;

    
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // 1. LISTAR PRODUCTOS (READ - Todos)
    // Devuelve el nombre de la vista: lista_productos (para lista_productos.jsp)
    @GetMapping({"/lista"})
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "lista_productos"; // Nombre de tu JSP
    }

    // 2. FORMULARIO DE CREACIÓN
    // Devuelve el nombr", "/e de la vista: form_producto (para form_producto.jsp)
    @GetMapping("/new")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        return "form_producto"; // Nombre de tu JSP
    }

    // 3. FORMULARIO DE EDICIÓN
    // Devuelve el nombre de la vista: form_producto (para form_producto.jsp)
    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productoService.findById(id);
        
        if (productoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado con ID: " + id);
            return "redirect:/admin/productos/web";
        }
        
        model.addAttribute("producto", productoOpt.get());
        return "form_producto"; // Nombre de tu JSP
    }

    // 4. GUARDAR O ACTUALIZAR (CREATE/UPDATE)
    @PostMapping({"", "/"})
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        productoService.save(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto guardado con éxito!");
        return "redirect:/admin/productos/web"; 
    }
    
    // 5. ELIMINAR
    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto.");
        }
        return "redirect:/admin/productos/web";
    }
}