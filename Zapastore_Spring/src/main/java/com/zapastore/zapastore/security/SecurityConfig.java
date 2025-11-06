package com.zapastore.zapastore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@Configuration // Marca esta clase como configuración de Spring
@EnableWebSecurity // Activa la configuración de seguridad web de Spring
public class SecurityConfig {

    private final UsuarioDetailsServiceImpl usuarioDetailsService; 
    // Servicio que carga los usuarios desde la base de datos (implementa UserDetailsService)

    public SecurityConfig(UsuarioDetailsServiceImpl usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService; // Inyección del servicio
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Codificador de contraseñas usando BCrypt (recomendado para producción)
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Proveedor de autenticación que usa tu servicio de usuarios y codificador de contraseñas
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService); // Define cómo se cargan usuarios
        authProvider.setPasswordEncoder(passwordEncoder()); // Define cómo se compara la contraseña
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
    .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // Todas las rutas bajo /user/** son públicas
            .requestMatchers("/login", "/registrar", "/css/**", "/img/**", "/user/**").permitAll()
            // Admin y cliente requieren rol
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/cliente/**", "/carrito/**").hasRole("CLIENTE")
            // Cualquier otra ruta, también pública por ahora
            .anyRequest().permitAll()
        )
        .formLogin(form -> form
            .loginPage("/login")                // Página de login personalizada
            .usernameParameter("correo")      // <- coincide con tu input name
            .passwordParameter("contrasena") 
            .successHandler(authenticationSuccessHandler()) // Maneja redirección según rol
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
        .authenticationProvider(authenticationProvider());

        return http.build(); // Construye la cadena de filtros de seguridad
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        // Controla la redirección después del login según el rol del usuario
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication)
                                                throws IOException, ServletException {
                // Obtiene el primer rol del usuario (generalmente solo uno)
                String role = authentication.getAuthorities().stream()
                        .map(auth -> auth.getAuthority())
                        .findFirst()
                        .orElse("");

                // Redirecciones según rol
                if (role.equals("ROLE_ADMIN")) {
                    // Si es admin, va al dashboard de administrador
                    response.sendRedirect(request.getContextPath() + "/admin/usuarios/lista");
                } else if (role.equals("ROLE_CLIENTE")) {
                    // Si es cliente, va a la home del cliente
                    response.sendRedirect(request.getContextPath() + "/cliente/home");
                } else {
                    // Si no tiene rol (público o prueba), va a la página de usuario público
                    response.sendRedirect(request.getContextPath() + "/user/home"); 
                }
            }
        };
    }
}
