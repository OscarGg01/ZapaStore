package com.zapastore.zapastore.model.detalle_pedido;

import com.zapastore.zapastore.model.pedidos.Pedido;
import com.zapastore.zapastore.model.pedidos.PedidoService;
import com.zapastore.zapastore.model.producto.Producto;
import com.zapastore.zapastore.model.producto.ProductoService;
import com.zapastore.zapastore.model.usuario.Usuario;
import com.zapastore.zapastore.model.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@PreAuthorize("hasRole('CLIENTE')")
@Controller
@RequestMapping("/carrito")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @Autowired
    public DetallePedidoController(DetallePedidoService detallePedidoService,
                                   ProductoService productoService,
                                   PedidoService pedidoService,
                                   UsuarioService usuarioService) {
        this.detallePedidoService = detallePedidoService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String verCarrito(Model model, Authentication authentication) {
        // Obtener el usuario logeado
        Usuario cliente = usuarioService.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar pedidos pendientes del usuario
        List<Pedido> pedidosUsuario = pedidoService.findByCliente(cliente);
        Pedido pedido;
        if (pedidosUsuario.isEmpty()) {
            pedido = new Pedido();
            pedido.setCliente(cliente); // Se usa String en vez de Usuario
            pedido.setTotalPagar(BigDecimal.ZERO);
            pedido.setCostoEnvio(BigDecimal.ZERO);
            pedido.setFecha(LocalDateTime.now());
            pedido.setEstado("Pendiente");
            pedido = pedidoService.save(pedido);
        } else {
            pedido = pedidosUsuario.get(0);
        }

        List<DetallePedido> carrito = detallePedidoService.listarPorPedido(pedido.getId());
        int totalItems = carrito.stream().mapToInt(DetallePedido::getCantidad).sum();
        BigDecimal totalPagar = carrito.stream()
                .map(DetallePedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("carrito", carrito);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPagar", totalPagar);

        return "cliente/carrito";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Integer productoId,
                                   @RequestParam Integer cantidad,
                                   @RequestParam Integer talla,
                                   RedirectAttributes redirectAttributes,
                                   Authentication authentication) {

        Usuario cliente = usuarioService.findByCorreo(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoService.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<Pedido> pedidosUsuario = pedidoService.findByCliente(cliente);
        Pedido pedido;
        if (pedidosUsuario.isEmpty()) {
            pedido = new Pedido();
            pedido.setCliente(cliente);; // Se usa String en vez de Usuario
            pedido.setTotalPagar(BigDecimal.ZERO);
            pedido.setCostoEnvio(BigDecimal.ZERO);
            pedido.setFecha(LocalDateTime.now());
            pedido.setEstado("Pendiente");
            pedido = pedidoService.save(pedido);
        } else {
            pedido = pedidosUsuario.get(0);
        }

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setTalla(talla);
        detalle.setPrecioUnitario(producto.getPrecio());
        detalle.setNombreProducto(producto.getNombre());
        detalle.calcularSubtotal();

        detallePedidoService.save(detalle);

        redirectAttributes.addFlashAttribute("mensaje", "Producto agregado al carrito");
        return "redirect:/carrito";
    }

    @PostMapping("/actualizar")
    public String actualizarCarrito(@RequestParam Integer detalleId,
                                    @RequestParam Integer cantidad,
                                    @RequestParam Integer talla,
                                    RedirectAttributes redirectAttributes) {

        DetallePedido detalle = detallePedidoService.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));

        detalle.setCantidad(cantidad);
        detalle.setTalla(talla);
        detalle.calcularSubtotal();

        detallePedidoService.save(detalle);

        redirectAttributes.addFlashAttribute("mensaje", "Carrito actualizado");
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarDelCarrito(@RequestParam Integer detalleId,
                                     RedirectAttributes redirectAttributes) {
        detallePedidoService.deleteById(detalleId);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado del carrito");
        return "redirect:/carrito";
    }
}
