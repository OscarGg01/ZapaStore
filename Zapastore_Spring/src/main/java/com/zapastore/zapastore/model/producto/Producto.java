package com.zapastore.zapastore.model.producto;

import jakarta.persistence.*;
import java.math.BigDecimal; // IMPORTANTE: Para precisión del dinero

@Entity
@Table(name = "productos") 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_ID") 
    private Integer id; // Mantenemos Integer para el ID

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "categoria_ID", nullable = false) 
    private Integer categoriaID;

    @Column(name = "Precio")
    private BigDecimal precio; // CAMBIO CLAVE: Ahora es BigDecimal

    @Column(name = "img_Url")
    private String imagen_url;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // Constructores
    public Producto() {}

    public Producto(Integer id, String nombre, Integer categoriaID, BigDecimal precio, // Cambio aquí
                    String imagen_url, String descripcion) { 
        this.id = id;
        this.nombre = nombre;
        this.categoriaID = categoriaID;
        this.precio = precio;
        this.imagen_url = imagen_url;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(Integer categoriaID) {
        this.categoriaID = categoriaID;
    }

    public BigDecimal getPrecio() { // Cambio aquí
        return precio;
    }

    public void setPrecio(BigDecimal precio) { // Cambio aquí
        this.precio = precio;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}