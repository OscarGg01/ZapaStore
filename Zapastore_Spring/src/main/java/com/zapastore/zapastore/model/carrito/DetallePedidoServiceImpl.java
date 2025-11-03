package com.zapastore.zapastore.model.carrito;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<DetallePedido> listarTodo() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido agregar(DetallePedido detalle) {
        // Si la columna subtotal se calcula en BD como GENERATED ALWAYS, no es necesario setear.
        // Pero si quieres asegurarte:
        if (detalle.getPrecio_unitario() != null && detalle.getCantidad() != null) {
            detalle.setSubtotal(detalle.getPrecio_unitario() * detalle.getCantidad());
        }
        return detallePedidoRepository.save(detalle);
    }

    @Override
    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public Double calcularTotal() {
        return detallePedidoRepository.findAll()
                .stream()
                .mapToDouble(d -> (d.getSubtotal() == null ? 0.0 : d.getSubtotal()))
                .sum();
    }
}
