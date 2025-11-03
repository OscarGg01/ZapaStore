package com.zapastore.zapastore.model.carrito;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedido_Detalle") // coincide con schema.sql/data.sql
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidodetalle_ID;

    private Integer pedido_ID;
    private Integer producto_ID;
    private String nombre_producto;
    private Double precio_unitario;
    private Integer cantidad;

    // En schema.sql el subtotal es columna generada; permitimos leerla
    @Column(insertable = false, updatable = false)
    private Double subtotal;

    public DetallePedido() {}

    // constructor útil en pruebas
    public DetallePedido(Integer pedido_ID, Integer producto_ID, String nombre_producto,
                         Double precio_unitario, Integer cantidad) {
        this.pedido_ID = pedido_ID;
        this.producto_ID = producto_ID;
        this.nombre_producto = nombre_producto;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
    }

    // getters y setters
    public Integer getPedidodetalle_ID() { return pedidodetalle_ID; }
    public void setPedidodetalle_ID(Integer pedidodetalle_ID) { this.pedidodetalle_ID = pedidodetalle_ID; }

    public Integer getPedido_ID() { return pedido_ID; }
    public void setPedido_ID(Integer pedido_ID) { this.pedido_ID = pedido_ID; }

    public Integer getProducto_ID() { return producto_ID; }
    public void setProducto_ID(Integer producto_ID) { this.producto_ID = producto_ID; }

    public String getNombre_producto() { return nombre_producto; }
    public void setNombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }

    public Double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(Double precio_unitario) { this.precio_unitario = precio_unitario; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
