package org.example.Controlador;

import org.example.Modelo.Producto;
import org.example.Servicio.ProductoServicio;
import org.example.Vista.ProductoVista;

public class ProductoControlador {
    private final ProductoServicio servicio;
    private final ProductoVista vista;

    public ProductoControlador(ProductoServicio servicio, ProductoVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 0 -> vista.mostrarMensaje("Volviendo...");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void registrar() {
        ProductoVista.ProductoInput datos = vista.pedirDatos();
        try {
            servicio.registrar(new Producto(0, datos.nombre, datos.marca, datos.categoria));
            vista.mostrarMensaje("✅ Producto registrado.");
        } catch (Exception e) {
            vista.mostrarMensaje("❌ Error: " + e.getMessage());
        }
    }

    private void listar() {
        vista.mostrarMensaje("\n--- LISTA DE PRODUCTOS ---");
        servicio.listar().forEach(p -> vista.mostrarMensaje(p.toString()));
    }
}