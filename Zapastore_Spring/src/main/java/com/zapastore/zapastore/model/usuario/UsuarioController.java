package com.zapastore.zapastore.model.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 🟢 Mostrar formulario para crear nuevo usuario
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioCrear";
    }

    // 🟢 Guardar nuevo usuario
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario,
                               RedirectAttributes redirectAttributes) {
        usuario.setIdUsuario("USR" + System.currentTimeMillis());
        usuarioService.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado correctamente");
        return "redirect:/usuario/lista";
    }

    // 🟢 Mostrar lista de usuarios
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarioLista";
    }

    // 🟢 Mostrar formulario de edición
    @GetMapping("/editar/{idUsuario}")
    public String mostrarFormularioEditar(@PathVariable("idUsuario") String idUsuario, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(idUsuario);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "usuarioEditar"; // JSP: usuarioEditar.jsp
        } else {
            return "redirect:/usuario/lista";
        }
    }

    // 🟢 Actualizar usuario (FALTABA ESTE MÉTODO)
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                    RedirectAttributes redirectAttributes) {
        // Verificar si el usuario existe
        Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(usuario.getIdUsuario());

        if (usuarioExistente.isPresent()) {
            // Reutilizar la contraseña si no fue cambiada
            if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                usuario.setContrasena(usuarioExistente.get().getContrasena());
            }

            usuarioService.guardarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "El usuario no existe");
        }

        return "redirect:/usuario/lista";
    }

    // 🟢 Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") String id,
                                  RedirectAttributes redirectAttributes) {
        usuarioService.eliminarUsuario(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        return "redirect:/usuario/lista";
    }
}
