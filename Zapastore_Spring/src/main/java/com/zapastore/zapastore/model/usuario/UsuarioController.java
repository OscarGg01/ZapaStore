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

    // 🟢 Mostrar lista de usuarios
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarioLista";
    }

    // 🟢 Mostrar formulario (crear o editar)
    @GetMapping({"/crear", "/editar/{idUsuario}"})
    public String mostrarFormulario(@PathVariable(required = false) String idUsuario, Model model) {
        if (idUsuario != null) {
            Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(idUsuario);
            if (usuarioOpt.isPresent()) {
                model.addAttribute("usuario", usuarioOpt.get());
            } else {
                return "redirect:/admin/usuarios/lista";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return "usuarioForm"; // 👉 JSP actualizado
    }

    // 🟢 Guardar o actualizar usuario (unificado)
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                 RedirectAttributes redirectAttributes) {

        if (usuario.getIdUsuario() == null || usuario.getIdUsuario().isEmpty()) {
            // Crear nuevo usuario
            usuario.setIdUsuario("USR" + System.currentTimeMillis());
            usuarioService.guardarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario creado correctamente");
        } else {
            // Actualizar existente
            Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(usuario.getIdUsuario());
            if (usuarioExistente.isPresent()) {
                if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                    usuario.setContrasena(usuarioExistente.get().getContrasena());
                }
                usuarioService.guardarUsuario(usuario);
                redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "El usuario no existe");
            }
        }

        return "redirect:/admin/usuarios/lista";
    }

    // 🟢 Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") String id,
                                  RedirectAttributes redirectAttributes) {
        usuarioService.eliminarUsuario(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        return "redirect:/admin/usuarios/lista";
    }
}
