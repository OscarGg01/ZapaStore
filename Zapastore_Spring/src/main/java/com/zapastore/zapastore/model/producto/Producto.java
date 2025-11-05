package com.zapastore.zapastore.model.producto;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_ID")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "categoria_ID", nullable = false)
    private Integer categoriaID;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "img_Url")
    private String imagen_url;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // ===== NUEVO =====
    @Transient
    private String categoriaNombre;

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    // --- Constructores ---
    public Producto() {}

    public Producto(Integer id, String nombre, Integer categoriaID, BigDecimal precio, String imagen_url, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaID = categoriaID;
        this.precio = precio;
        this.imagen_url = imagen_url;
        this.descripcion = descripcion;
    }

    // --- Getters y Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCategoriaID() { return categoriaID; }
    public void setCategoriaID(Integer categoriaID) { this.categoriaID = categoriaID; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
