package com.zapastore.zapastore.model.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull; // Asegúrate de que esta importación esté presente

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repository;

    @Override
    // Línea 21 (Anteriormente línea 17): Añadir @NonNull al parámetro
    public List<DetallePedido> listarPorPedido(@NonNull Long pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }

    @Override
    // Línea 28/29: Añadir @NonNull al retorno y al parámetro para resolver las advertencias 67109781 y 16778128
    @NonNull 
    public DetallePedido guardar(@NonNull DetallePedido detalle) {
        return repository.save(detalle); 
    }

    @Override
    // Línea 33/34: Añadir @NonNull al parámetro para resolver las advertencias 67109781 y 16778128
    public void eliminar(@NonNull Long id) {
        repository.deleteById(id);
    }
}