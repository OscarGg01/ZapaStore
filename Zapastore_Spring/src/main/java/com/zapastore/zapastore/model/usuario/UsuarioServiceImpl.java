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
    public Usuario guardarUsuario(@NonNull Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @SuppressWarnings("all")
    public Optional<Usuario> obtenerUsuarioPorId(@NonNull String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    @SuppressWarnings("all")
    public void eliminarUsuario(@NonNull String idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
