package org.example.Controlador;

import org.example.Modelo.Supermercado;
import org.example.Servicio.SupermercadoServicio;
import org.example.Vista.SupermercadoVista;

public class SupermercadoControlador {
    private final SupermercadoServicio servicio;
    private final SupermercadoVista vista;

    public SupermercadoControlador(SupermercadoServicio servicio, SupermercadoVista vista) {
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
                case 3 -> eliminar();
                case 0 -> vista.mostrarMensaje("Volviendo...");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void registrar() {
        SupermercadoVista.SupermercadoInput datos = vista.pedirDatos();
        try {
            servicio.registrar(new Supermercado(0, datos.nombre, datos.ubicacion));
            vista.mostrarMensaje("✅ Supermercado registrado.");
        } catch (Exception e) {
            vista.mostrarMensaje("❌ Error: " + e.getMessage());
        }
    }

    private void listar() {
        servicio.listar().forEach(s ->
                vista.mostrarMensaje("ID: " + s.getId() + " | " + s.getNombre() + " (" + s.getUbicacion() + ")")
        );
    }

    private void eliminar() {
        int id = vista.pedirId();
        servicio.eliminar(id);
        vista.mostrarMensaje("Operación de eliminación ejecutada.");
    }
}