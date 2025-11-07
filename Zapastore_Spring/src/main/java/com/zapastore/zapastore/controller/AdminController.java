package com.zapastore.zapastore.controller;

import com.zapastore.zapastore.model.pedidos.MetricaService;
import com.zapastore.zapastore.model.pedidos.dto.PedidoDiaDTO;
import com.zapastore.zapastore.model.pedidos.dto.DiaSemanaDTO;
import com.zapastore.zapastore.model.pedidos.dto.MesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class AdminController {

    private final MetricaService metricaService;

    @Autowired
    public AdminController(MetricaService metricaService) {
        this.metricaService = metricaService;
    }

    @GetMapping("/admin/metricas")
    public String showDashboard(
            @RequestParam(value = "dia", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia,

            @RequestParam(value = "semana", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSemana,

            @RequestParam(value = "mesInicio", required = false) Integer mesInicio,
            @RequestParam(value = "mesFin", required = false) Integer mesFin,
            @RequestParam(value = "anio", required = false) Integer anio,
            Model model) {

        // --- Ventas por Día ---
        LocalDate diaSeleccionado = dia != null ? dia : LocalDate.now();
        BigDecimal totalDia = metricaService.totalPorDia(diaSeleccionado);
        int pedidosDia = metricaService.cantidadPedidosPorDia(diaSeleccionado);
        List<PedidoDiaDTO> listaPedidosDia = metricaService.pedidosPorDia(diaSeleccionado);

        model.addAttribute("totalDia", totalDia);
        model.addAttribute("pedidosDia", pedidosDia);
        model.addAttribute("listaPedidosDia", listaPedidosDia);

        // --- Ventas por Semana ---
        LocalDate inicioSemana = fechaSemana != null ? fechaSemana.minusDays(6) : LocalDate.now().minusDays(6);
        LocalDate finSemana = fechaSemana != null ? fechaSemana : LocalDate.now();
        BigDecimal totalSemana = metricaService.totalPorRango(inicioSemana, finSemana);
        int pedidosSemana = metricaService.cantidadPedidosPorRango(inicioSemana, finSemana);
        List<DiaSemanaDTO> listaDiasSemana = metricaService.ventasPorDiaEnRango(inicioSemana, finSemana);

        model.addAttribute("totalSemana", totalSemana);
        model.addAttribute("pedidosSemana", pedidosSemana);
        model.addAttribute("listaDiasSemana", listaDiasSemana);

        // --- Ventas por Mes ---
        LocalDate inicioMes = (mesInicio != null && anio != null) ? LocalDate.of(anio, mesInicio, 1) : LocalDate.now().withDayOfMonth(1);
        LocalDate finMes = (mesFin != null && anio != null) ? LocalDate.of(anio, mesFin, 1).with(TemporalAdjusters.lastDayOfMonth()) : LocalDate.now();
        BigDecimal totalMes = metricaService.totalPorRango(inicioMes, finMes);
        int pedidosMes = metricaService.cantidadPedidosPorRango(inicioMes, finMes);
        List<MesDTO> listaMeses = metricaService.ventasPorMesEnRango(inicioMes, finMes);

        model.addAttribute("totalMes", totalMes);
        model.addAttribute("pedidosMes", pedidosMes);
        model.addAttribute("listaMeses", listaMeses);

        // --- Enviar valores para filtros ---
        model.addAttribute("diaSeleccionado", diaSeleccionado);
        model.addAttribute("fechaSemana", finSemana);
        model.addAttribute("mesInicio", mesInicio != null ? mesInicio : inicioMes.getMonthValue());
        model.addAttribute("mesFin", mesFin != null ? mesFin : finMes.getMonthValue());
        model.addAttribute("anio", anio != null ? anio : inicioMes.getYear());

        return "admin/metricas";
    }
}
