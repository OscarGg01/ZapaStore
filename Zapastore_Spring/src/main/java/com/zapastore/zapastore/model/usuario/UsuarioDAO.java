package com.zapastore.zapastore.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // Optional<Usuario> findByCorreo(String correo);
}
