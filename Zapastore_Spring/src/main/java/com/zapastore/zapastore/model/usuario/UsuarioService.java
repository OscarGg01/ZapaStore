package com.zapastore.zapastore.model.usuario;

import org.springframework.lang.NonNull; // ¡Añadir esta importación!
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    
    // Solución para la advertencia en línea 20 de UsuarioServiceImpl
    @NonNull 
    Usuario guardarUsuario(@NonNull Usuario usuario);
    
    // Los métodos findAll, findById, deleteById se asumen no nulos o manejan Optional
    List<Usuario> obtenerTodosLosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(String idUsuario);
    void eliminarUsuario(String idUsuario);
}