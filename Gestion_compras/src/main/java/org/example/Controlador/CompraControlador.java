package org.example.Controlador;

import org.example.Dto.ComparacionPrecioDTO;
import org.example.Modelo.Compra;
import org.example.Modelo.Usuario;
import org.example.Servicio.CompraServicio;
import org.example.Vista.CompraVista;

import java.time.LocalDate;
import java.util.List;

public class CompraControlador {
    private final CompraServicio servicio;
    private final CompraVista vista;
    private final Usuario usuarioLogueado; // Necesario para saber quién registra

    // Inyección de dependencias en el constructor
    public CompraControlador(CompraServicio servicio, CompraVista vista, Usuario usuario) {
        this.servicio = servicio;
        this.vista = vista;
        this.usuarioLogueado = usuario;
    }

    public void iniciar() throws Exception {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> registrarCompra();
                case 2 -> consultarMejorPrecio();
                case 3 -> verHistorial();
                case 0 -> vista.mostrarMensaje("Saliendo del módulo...");
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void registrarCompra() {
        // 1. El controlador pide los datos a la vista
        CompraVista.RegistroCompraInput datos = vista.pedirDatosCompra();

        if (datos == null) return; // Si hubo error en la vista

        // 2. El controlador crea el modelo
        Compra nuevaCompra = new Compra(
                0,
                usuarioLogueado.getId(),
                datos.idProducto,
                datos.idSupermercado,
                datos.precio,
                datos.cantidad,
                LocalDate.now()
        );

        // 3. El controlador llama al servicio
        try {
            servicio.registrarCompra(nuevaCompra);
            // 4. El controlador actualiza la vista con el resultado
            vista.mostrarMensaje("✅ Compra registrada con éxito.");
        } catch (Exception e) {
            vista.mostrarMensaje("❌ Error al registrar: " + e.getMessage());
        }
    }

    private void consultarMejorPrecio() {
        int idProd = vista.pedirIdProducto();
        if (idProd == -1) {
            vista.mostrarMensaje("ID inválido.");
            return;
        }

        try {
            ComparacionPrecioDTO mejor = servicio.obtenerMejorOpcion(idProd);
            vista.mostrarMensaje("\n--- MEJOR OPCIÓN ---");
            vista.mostrarMensaje("Supermercado: " + mejor.getNombreSupermercado());
            vista.mostrarMensaje("Precio: $" + mejor.getPrecio());
        } catch (Exception e) {
            vista.mostrarMensaje("Información: " + e.getMessage());
        }
    }

    private void verHistorial() throws Exception {
        int idProd = vista.pedirIdProducto();
        List<ComparacionPrecioDTO> historial = servicio.obtenerHistorialComparativo(idProd);

        if (historial.isEmpty()) {
            vista.mostrarMensaje("No hay historial para este producto.");
        } else {
            vista.mostrarMensaje("\n--- HISTORIAL DE PRECIOS ---");
            for (ComparacionPrecioDTO item : historial) {
                vista.mostrarMensaje(item.getNombreSupermercado() + " -> $" + item.getPrecio());
            }
        }
    }
}