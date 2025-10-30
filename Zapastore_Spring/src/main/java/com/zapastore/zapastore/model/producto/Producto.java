package com.zapastore.zapastore.model.producto;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos") 
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
    private Double precio;

    @Column(name = "img_Url")
    private String imagen_url;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // *** ELIMINADO: private Integer stock; ***

    // Constructores
    public Producto() {}

    public Producto(Integer id, String nombre, Integer categoriaID, Double precio,
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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

    // *** ELIMINADO: Getters y Setters para 'stock' ***
}