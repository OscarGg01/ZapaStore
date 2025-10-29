package com.zapastore.zapastore.model.categoria;

import jakarta.persistence.*;
/**
 * Mapea la tabla 'Categorias' de la base de datos.
 */
@Entity
@Table(name = "Categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_ID")
    private Integer categoriaId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "estado", length = 20)
    private String estado; // Por defecto 'Activo' según schema.sql

    // --- Constructores ---
    public Categoria() {
    }

    public Categoria(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    // --- Getters y Setters ---

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Opcional: toString para debugging
    @Override
    public String toString() {
        return "Categoria{" +
                "categoriaId=" + categoriaId +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}