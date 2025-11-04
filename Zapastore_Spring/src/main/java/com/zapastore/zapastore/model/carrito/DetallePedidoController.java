package com.zapastore.zapastore.model.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carrito")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    // Mostrar carrito (vista JSP)
    @GetMapping("/{pedidoId}")
    public String mostrarCarrito(@PathVariable Long pedidoId, Model model) {
        List<DetallePedido> detalles = detallePedidoService.listarPorPedido(pedidoId);
        model.addAttribute("detalles", detalles);
        return "carrito"; // esto carga carrito.jsp
    }

    // Eliminar un producto del carrito
    @GetMapping("/eliminar/{id}/{pedidoId}")
    public String eliminarDetalle(@PathVariable Long id, @PathVariable Long pedidoId) {
        detallePedidoService.eliminar(id);
        return "redirect:/carrito/" + pedidoId; // recarga el carrito con los productos actualizados
    }

    // Agregar o actualizar un producto (opcional)
    @PostMapping("/guardar")
    public String guardarDetalle(@ModelAttribute DetallePedido detallePedido) {
        detallePedidoService.guardar(detallePedido);
        return "redirect:/carrito/" + detallePedido.getPedidoId();
    }
}
