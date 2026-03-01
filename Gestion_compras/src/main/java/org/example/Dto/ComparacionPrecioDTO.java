package org.example.Dto;

import java.time.LocalDate;

public class ComparacionPrecioDTO {
    private String nombreSupermercado;
    private double precio;
    private LocalDate fecha;

    public ComparacionPrecioDTO(String nombreSupermercado, double precio, LocalDate fecha) {
        this.nombreSupermercado = nombreSupermercado;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getNombreSupermercado() {
        return nombreSupermercado;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Supermercado: " + nombreSupermercado + " | Precio: $" + precio + " | Fecha: " + fecha;
    }
}