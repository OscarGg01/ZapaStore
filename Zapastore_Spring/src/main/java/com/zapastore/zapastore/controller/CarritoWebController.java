package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.carrito.DetallePedido;
import com.zapastore.zapastore.model.carrito.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CarritoWebController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/carrito")
    public String mostrarCarrito(@RequestParam(value="pedidoId", required=false) Long pedidoId, Model model) {
        List<DetallePedido> detalles;

        if(pedidoId != null) {
            detalles = detallePedidoService.listarPorPedido(pedidoId);
        } else {
            detalles = List.of(); // carrito vacío si no hay pedido
        }

        double total = detalles.stream().mapToDouble(d -> d.getSubtotal() != null ? d.getSubtotal() : 0).sum();

        model.addAttribute("listaDetalles", detalles);
        model.addAttribute("total", total);
        model.addAttribute("cantidadProductos", detalles.size());

        return "carrito";
    }
}
