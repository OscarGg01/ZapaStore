package com.zapastore.zapastore.model.pedidos.dto;

import java.math.BigDecimal;

public class PedidoDiaDTO {
    private String hora;
    private BigDecimal total;

    public PedidoDiaDTO(String hora, BigDecimal total) {
        this.hora = hora;
        this.total = total;
    }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}