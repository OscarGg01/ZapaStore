package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.usuario.Usuario;
import com.zapastore.zapastore.model.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login"; // JSP login.jsp
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario,
                                HttpSession session,
                                Model model) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(usuario.getCorreo());

        if (usuarioOpt.isPresent()) {
            Usuario usuarioDB = usuarioOpt.get();

            // Validar contraseña
            if (usuarioDB.getContrasena().equals(usuario.getContrasena())
                    || usuarioDB.getContrasena().equals("{noop}" + usuario.getContrasena())) {

                // Guardar usuario en sesión
                session.setAttribute("usuarioLogueado", usuarioDB);

                // Redirigir siempre a /admin/categorias/lista
                return "redirect:/admin/categorias/lista";

            } else {
                model.addAttribute("error", "Contraseña incorrecta");
            }

        } else {
            model.addAttribute("error", "Correo no registrado");
        }

        return "login"; // Retorna al formulario con mensaje de error
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
