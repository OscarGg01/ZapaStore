package com.zapastore.zapastore.model.pedidos.dto;

import java.math.BigDecimal;

public class MesDTO {
    private String mes;
    private BigDecimal total;

    public MesDTO(String mes, BigDecimal total) {
        this.mes = mes;
        this.total = total;
    }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}