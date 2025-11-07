package com.zapastore.zapastore.model.pedidos;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zapastore.zapastore.model.pedidos.dto.PedidoDiaDTO;
import com.zapastore.zapastore.model.pedidos.dto.DiaSemanaDTO;
import com.zapastore.zapastore.model.pedidos.dto.MesDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricaService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public MetricaService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // ------------------- VENTAS POR DÍA -------------------
    public List<PedidoDiaDTO> pedidosPorDia(LocalDate dia) {
        DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime inicio = dia.atStartOfDay();
        LocalDateTime fin = dia.atTime(23, 59, 59);

        return pedidoRepository.findByFechaBetween(inicio, fin)
                .stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .sorted(Comparator.comparing(Pedido::getFecha)) // Ordena por fecha/hora ascendente
                .map(p -> new PedidoDiaDTO(
                        p.getFecha().format(horaFormat),
                        p.getTotalPagar()
                ))
                .collect(Collectors.toList());
    }

    // ------------------- VENTAS POR SEMANA -------------------
    public List<DiaSemanaDTO> ventasPorDiaEnRango(LocalDate inicio, LocalDate fin) {
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime finDateTime = fin.atTime(23, 59, 59);

        return pedidoRepository.findByFechaBetween(inicioDateTime, finDateTime)
                .stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .map(p -> new DiaSemanaDTO(fechaFormat.format(p.getFecha().toLocalDate()), p.getTotalPagar()))
                .collect(Collectors.toList());
    }

    // ------------------- VENTAS POR MES -------------------
    public List<MesDTO> ventasPorMesEnRango(LocalDate inicio, LocalDate fin) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime finDateTime = fin.atTime(23, 59, 59);

        return pedidoRepository.findByFechaBetween(inicioDateTime, finDateTime)
                .stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .map(p -> {
                    String mes = p.getFecha().getMonth().getDisplayName(TextStyle.SHORT, new Locale("es"));
                    String anio = String.valueOf(p.getFecha().getYear());
                    return new MesDTO(mes + " " + anio, p.getTotalPagar());
                })
                .collect(Collectors.toList());
    }

    // ------------------- MÉTODOS RESUMEN -------------------
    public BigDecimal totalPorDia(LocalDate dia) {
        return pedidosPorDia(dia).stream()
                .map(PedidoDiaDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int cantidadPedidosPorDia(LocalDate dia) {
        return pedidosPorDia(dia).size();
    }

    public BigDecimal totalPorRango(LocalDate inicio, LocalDate fin) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime finDateTime = fin.atTime(23, 59, 59);

        return pedidoRepository.findByFechaBetween(inicioDateTime, finDateTime)
                .stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .map(Pedido::getTotalPagar)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int cantidadPedidosPorRango(LocalDate inicio, LocalDate fin) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime finDateTime = fin.atTime(23, 59, 59);

        return (int) pedidoRepository.findByFechaBetween(inicioDateTime, finDateTime)
                .stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstado()))
                .count();
    }
}
