package com.zapastore.zapastore.model.usuario;

import com.zapastore.zapastore.model.pedidos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    @Autowired
    public PerfilController(UsuarioService usuarioService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("cliente/perfil")
    public String verPerfil(Authentication authentication, Model model) {
        Usuario cliente = usuarioService.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Formato de fecha hasta minutos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Obtener solo pedidos completados y formatear la fecha como String
        List<PedidoView> pedidosCompletados = pedidoService.findByCliente(cliente).stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .map(p -> new PedidoView(
                        p.getId(),
                        p.getTotalPagar(),
                        p.getEstado(),
                        p.getFecha().format(formatter)  // formateo aquí
                ))
                .collect(Collectors.toList());

        model.addAttribute("pedidos", pedidosCompletados);
        return "cliente/perfil"; // JSP de perfil
    }

    // DTO para la vista
    public static class PedidoView {
        private Integer id;
        private java.math.BigDecimal totalPagar;
        private String estado;
        private String fecha;

        public PedidoView(Integer id, java.math.BigDecimal totalPagar, String estado, String fecha) {
            this.id = id;
            this.totalPagar = totalPagar;
            this.estado = estado;
            this.fecha = fecha;
        }

        // Getters
        public Integer getId() { return id; }
        public java.math.BigDecimal getTotalPagar() { return totalPagar; }
        public String getEstado() { return estado; }
        public String getFecha() { return fecha; }
    }
}
