package com.zapastore.zapastore.model.producto;

import com.zapastore.zapastore.model.categoria.Categoria;
import com.zapastore.zapastore.model.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService; 

    @Autowired
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    // --- LÓGICA AUXILIAR ---

    /**
     * Carga la lista completa de Categorías y la añade al modelo para los formularios.
     */
    private void cargarCategoriasParaFormulario(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
    }
    
    /**
     * Asigna el nombre de la categoría a cada producto utilizando un mapa de IDs.
     * Esto es necesario porque la entidad Producto no tiene una relación JPA con Categoria.
     */
    private void asignarNombresCategorias(List<Producto> productos) {
        // 1. Obtener todas las categorías y mapearlas a ID -> Nombre
        Map<Integer, String> categoriaMap = categoriaService.findAll().stream()
                .collect(Collectors.toMap(
                    Categoria::getCategoriaId, // Usamos getCategoriaId() para mapear el ID
                    Categoria::getNombre
                ));

        // 2. Iterar sobre los productos y asignar el nombre (Transitorio)
        for (Producto producto : productos) {
            String nombre = categoriaMap.getOrDefault(producto.getCategoriaID(), "N/A");
            producto.setCategoriaNombre(nombre); // Asigna el nombre al campo @Transient
        }
    }


    // --- MÉTODOS DEL CONTROLADOR ---

    @GetMapping("/lista")
    public String listarProductos(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Producto> productos;
        
        // Lógica de búsqueda
        if (query != null && !query.trim().isEmpty()) {
            // DEBES IMPLEMENTAR este método en ProductoService y ProductoRepository
            productos = productoService.findByNombreContaining(query); 
            model.addAttribute("currentQuery", query); 
        } else {
            productos = productoService.findAll();
        }

        // Asignar el nombre de la categoría para la tabla de lista
        asignarNombresCategorias(productos); 
        
        model.addAttribute("productos", productos);
        return "admin/lista_productos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        cargarCategoriasParaFormulario(model); // Cargar categorías para el select
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
        cargarCategoriasParaFormulario(model); // Cargar categorías para el select
        return "admin/form_producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        // Asegúrate de que el precio no sea nulo si es requerido por la DB
        productoService.save(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto guardado con éxito!");
        return "redirect:/admin/productos/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productoService.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            // Soft delete: solo cambiar estado
            producto.setEstado("Inactivo");
            productoService.save(producto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto desactivado con éxito!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado.");
        }
        return "redirect:/admin/productos/lista";
    }
}