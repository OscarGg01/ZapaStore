package com.zapastore.zapastore.model.pedidos;

import com.zapastore.zapastore.model.usuario.Usuario;
import com.zapastore.zapastore.model.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CheckoutController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @Autowired
    public CheckoutController(PedidoService pedidoService,
                              UsuarioService usuarioService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/checkout")
    public String checkoutPost(Authentication authentication, RedirectAttributes redirectAttributes) {
        Usuario cliente = usuarioService.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Pedido> pedidosPendientes = pedidoService.findByClienteAndEstado(cliente, "Pendiente");
        if (pedidosPendientes.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensaje", "No hay pedidos pendientes para pagar.");
            return "redirect:/cliente/home";
        }

        Pedido pedido = pedidosPendientes.get(0);
        pedido.setEstado("Completado");
        pedido.setFecha(LocalDateTime.now());
        pedidoService.save(pedido);

        redirectAttributes.addFlashAttribute("pedidoExitoso", pedido);
        return "redirect:/checkout/success";
    }

    @GetMapping("/checkout/success")
    public String checkoutSuccess(Model model) {
        if (!model.containsAttribute("pedidoExitoso")) {
            return "redirect:/cliente/home";
        }
        return "cliente/compraExitosa";
    }

}
