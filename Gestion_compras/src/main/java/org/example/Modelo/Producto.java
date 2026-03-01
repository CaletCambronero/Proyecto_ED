package org.example.Modelo;

public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private String categoria;

    public Producto(int id, String nombre, String marca, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
    }

    public Producto() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Marca: %s | Cat: %s", id, nombre, marca, categoria);
    }
}