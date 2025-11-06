package com.zapastore.zapastore.model.detalle_pedido;

import com.zapastore.zapastore.model.pedidos.Pedido;
import com.zapastore.zapastore.model.pedidos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoService pedidoService;

    @Autowired
    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository,
                                    PedidoService pedidoService) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    public List<DetallePedido> listarPorPedido(Integer pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId).orElseThrow();
        return detallePedidoRepository.findByPedido(pedido);
    }

    @Override
    public Optional<DetallePedido> findById(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public void deleteById(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}
