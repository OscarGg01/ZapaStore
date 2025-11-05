package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.usuario.Usuario;
import com.zapastore.zapastore.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar formulario de registro
    @GetMapping("/registrar")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar"; // registrar.html o registrar.jsp
    }

    // Procesar registro
    @PostMapping("/registrar")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario usuario,
                                   @RequestParam("confirm-password") String confirmPassword,
                                   Model model) {

        // Verificar si el correo ya existe
        Optional<Usuario> existingUser = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Correo ya registrado");
            return "registrar";
        }

        // Verificar que las contraseñas coincidan
        if (!usuario.getContrasena().equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registrar";
        }

        // Asignar un ID único al usuario
        usuario.setIdUsuario(UUID.randomUUID().toString());

        // Asignar rol por defecto (cliente)
        usuario.setRol("CLIENTE");

        // Guardar usuario en la base de datos
        usuarioRepository.save(usuario);

        // Redirigir al login con mensaje de éxito
        model.addAttribute("success", "Registro exitoso. Por favor inicia sesión.");
        return "redirect:/login";
    }
}
