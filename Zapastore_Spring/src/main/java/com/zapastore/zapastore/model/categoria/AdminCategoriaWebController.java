package com.zapastore.zapastore.model.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controlador Web para la administración (CRUD) de Categorías.
 * Mapea las URLs que resuelven a vistas JSP.
 * URL Base: /admin/categorias
 */
@Controller
@RequestMapping("/admin/categorias")
public class AdminCategoriaWebController {

    private final CategoriaService categoriaService;

    @Autowired
    public AdminCategoriaWebController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // =========================================================
    // R (READ) - Listar categorías
    // Mapeo: GET /admin/categorias/lista
    // Vista: categoriaLista.jsp
    // =========================================================
    @GetMapping({"/lista", "/"})
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "categoriaLista";
    }

    // =========================================================
    // C (CREATE) - Guardar una nueva categoría (desde el formulario en lista)
    // Mapeo: POST /admin/categorias/guardar
    // Redirige a /admin/categorias/lista
    // =========================================================
    @PostMapping("/guardar")
    public String crearCategoria(@RequestParam("nombre") String nombre, 
                                 @RequestParam(value = "activo", required = false) boolean activo, // Mapea el select Activo/Inactivo
                                 RedirectAttributes redirectAttributes) {
        
        String estado = activo ? "Activo" : "Inactivo";
        
        // Crear nueva instancia (ID nulo, el service lo asigna)
        Categoria nuevaCategoria = new Categoria(nombre, estado);
        categoriaService.save(nuevaCategoria);
        
        redirectAttributes.addFlashAttribute("mensaje", "Categoría '" + nombre + "' creada exitosamente.");
        
        return "redirect:/admin/categorias/lista";
    }


    // =========================================================
    // U (UPDATE - Formulario) - Mostrar formulario de edición
    // Mapeo: GET /admin/categorias/editar?id={id}
    // Vista: categoriaEditar.jsp
    // =========================================================
    @GetMapping("/editar")
    public String mostrarFormularioEdicion(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Categoria> categoriaOpt = categoriaService.findById(id);

        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
            return "categoriaEditar";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Error: Categoría con ID " + id + " no encontrada para editar.");
            return "redirect:/admin/categorias/lista";
        }
    }
    
    // =========================================================
    // U (UPDATE - Acción) - Procesar la actualización
    // Mapeo: POST /admin/categorias/actualizar
    // Redirige a /admin/categorias/lista
    // =========================================================
    @PostMapping("/actualizar")
    public String actualizarCategoria(
            @ModelAttribute Categoria categoria, // Recibe la entidad con ID, nombre y estado
            RedirectAttributes redirectAttributes) {
        
        if (categoria.getCategoriaId() == null) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: ID de categoría es nulo para actualizar.");
            return "redirect:/admin/categorias/lista";
        }

        // Simplemente guardar, ya que el servicio maneja la actualización del estado y nombre
        categoriaService.save(categoria);

        redirectAttributes.addFlashAttribute("mensaje", "Categoría '" + categoria.getNombre() + "' actualizada exitosamente.");
        
        return "redirect:/admin/categorias/lista";
    }

    // =========================================================
    // D (SOFT DELETE) - Desactivar una categoría
    // Mapeo: GET /admin/categorias/eliminar?id={id}
    // Redirige a /admin/categorias/lista
    // =========================================================
    @GetMapping("/eliminar")
    public String desactivarCategoria(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            // El servicio realiza el Soft Delete (cambia el estado a 'Inactivo')
            categoriaService.deleteById(id); 
            redirectAttributes.addFlashAttribute("mensaje", "Categoría ID " + id + " desactivada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al desactivar la categoría ID " + id + ". Posiblemente no existe o tiene dependencias.");
        }
        
        return "redirect:/admin/categorias/lista";
    }
}