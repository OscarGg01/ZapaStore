package com.zapastore.zapastore.model.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    List<Usuario> obtenerTodosLosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(String idUsuario);
    void eliminarUsuario(String idUsuario);
}
