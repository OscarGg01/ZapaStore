package com.zapastore.zapastore.model.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/detalle")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/listar")
    @ResponseBody
    public List<DetallePedido> listar() {
        return detallePedidoService.listarTodo();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public DetallePedido guardar(@RequestBody DetallePedido detalle) {
        return detallePedidoService.agregar(detalle);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public void eliminar(@PathVariable Integer id) {
        detallePedidoService.eliminar(id);
    }
}
