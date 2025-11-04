package com.zapastore.zapastore.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull; // Importación necesaria

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @NonNull 
    // Usamos @NonNull aquí para satisfacer la advertencia de seguridad de tipo nulo (16778128)
    public Usuario guardarUsuario(@NonNull Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    // Línea 36: Reintroducir @NonNull para resolver la advertencia 16778128
    // Usamos @SuppressWarnings para evitar la advertencia 67109780 de redefinición que aparecería.
    @SuppressWarnings("all") 
    public Optional<Usuario> obtenerUsuarioPorId(@NonNull String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    // Línea 42: Reintroducir @NonNull para resolver la advertencia 16778128
    // Usamos @SuppressWarnings para evitar la advertencia 67109780 de redefinición que aparecería.
    @SuppressWarnings("all") 
    public void eliminarUsuario(@NonNull String idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}