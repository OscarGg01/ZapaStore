package com.zapastore.zapastore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zapastore.zapastore.model.usuario.Usuario;
import com.zapastore.zapastore.model.usuario.UsuarioRepository;

import java.util.Collections;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username) // <- aquí cambiamos
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                Collections.singletonList(authority)
        );
    }
}
