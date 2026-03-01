package org.example.Modelo;

import java.time.LocalDate;

public class Compra {
    private int id;
    private int idUsuario;
    private int idProducto;
    private int idSupermercado;
    private double precio;
    private int cantidad;
    private LocalDate fechaCompra;

    public Compra(int id, int idUsuario, int idProducto, int idSupermercado, double precio, int cantidad, LocalDate fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.idSupermercado = idSupermercado;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getIdSupermercado() { return idSupermercado; }
    public void setIdSupermercado(int idSupermercado) { this.idSupermercado = idSupermercado; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }
}