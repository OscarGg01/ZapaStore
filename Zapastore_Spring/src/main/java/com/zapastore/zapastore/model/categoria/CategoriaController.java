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
 * URL Base: /admin/categorias
 */
@Controller
@RequestMapping("/admin/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // ===============================================
    // R (READ) - Listar categorías
    // ===============================================
    @GetMapping({"/lista", "/"})
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "categoriaLista";
    }

    // ===============================================
    // C (CREATE) - Guardar nueva categoría
    // ===============================================
    @PostMapping("/guardar")
    public String crearCategoria(@RequestParam("nombre") String nombre,
                                 @RequestParam("estado") String estado,
                                 RedirectAttributes redirectAttributes) {

        Categoria nuevaCategoria = new Categoria(nombre, estado);
        categoriaService.save(nuevaCategoria);

        redirectAttributes.addFlashAttribute("mensaje", "Categoría '" + nombre + "' creada exitosamente.");
        return "redirect:/admin/categorias/lista";
    }

    // ===============================================
    // U (UPDATE - Formulario)
    // ===============================================
    @GetMapping("/editar")
    public String mostrarFormularioEdicion(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Categoria> categoriaOpt = categoriaService.findById(id);

        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
            return "categoriaEditar";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Categoría con ID " + id + " no encontrada.");
            return "redirect:/admin/categorias/lista";
        }
    }

    // ===============================================
    // U (UPDATE - Acción)
    // ===============================================
    @PostMapping("/actualizar")
    public String actualizarCategoria(@ModelAttribute Categoria categoria,
                                      RedirectAttributes redirectAttributes) {

        if (categoria.getCategoriaId() == null) {
            redirectAttributes.addFlashAttribute("mensaje", "ID de categoría es nulo.");
            return "redirect:/admin/categorias/lista";
        }

        categoriaService.save(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría '" + categoria.getNombre() + "' actualizada exitosamente.");
        return "redirect:/admin/categorias/lista";
    }

    // ===============================================
    // D (SOFT DELETE) - Desactivar categoría
    // ===============================================
    @GetMapping("/eliminar")
    public String desactivarCategoria(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría ID " + id + " desactivada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al desactivar la categoría ID " + id + ".");
        }
        return "redirect:/admin/categorias/lista";
    }
}
