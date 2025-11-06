package com.zapastore.zapastore.model.usuario;

import org.springframework.lang.NonNull;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    
    @NonNull 
    Usuario guardarUsuario(@NonNull Usuario usuario);
    
    List<Usuario> obtenerTodosLosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(String idUsuario);
    void eliminarUsuario(String idUsuario);

    // CORRECCIÓN: devuelve Usuario, no Producto
    Optional<Usuario> findByCorreo(String correo);
}
