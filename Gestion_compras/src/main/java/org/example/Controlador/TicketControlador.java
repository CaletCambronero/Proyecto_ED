package org.example.Controlador;

import org.example.Modelo.Ticket;
import org.example.Modelo.Usuario;
import org.example.Servicio.TicketServicio;
import org.example.Vista.TicketVista;

import java.time.LocalDateTime;

public class TicketControlador {
    private final TicketServicio servicio;
    private final TicketVista vista;
    private final Usuario usuarioLogueado;

    public TicketControlador(TicketServicio servicio, TicketVista vista, Usuario usuario) {
        this.servicio = servicio;
        this.vista = vista;
        this.usuarioLogueado = usuario;
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1 -> registrarTicket();
                case 2 -> listarMisTickets();
                case 0 -> vista.mostrarMensaje("Regresando...");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void registrarTicket() {
        TicketVista.TicketInput datos = vista.pedirDatosTicket();
        try {
            Ticket nuevo = new Ticket(
                    0,
                    usuarioLogueado.getId(),
                    datos.asunto,
                    datos.mensaje,
                    "PENDIENTE",
                    LocalDateTime.now()
            );

            servicio.crearTicket(nuevo);
            vista.mostrarMensaje("✅ Ticket creado y notificado al soporte.");
        } catch (Exception e) {
            vista.mostrarMensaje("❌ Error al crear ticket: " + e.getMessage());
        }
    }

    private void listarMisTickets() {
        vista.mostrarMensaje("\n--- MIS TICKETS ---");
        servicio.obtenerTicketsDeUsuario(usuarioLogueado.getId())
                .forEach(t -> vista.mostrarMensaje(t.toString()));
    }
}