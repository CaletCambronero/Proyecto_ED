package org.example.Servicio;

import org.example.Modelo.Ticket;
import org.example.Repositorio.TicketRepositorio;
import java.util.List;

public class TicketServicio {
    private final TicketRepositorio repositorio;

    public TicketServicio(TicketRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void crearTicket(Ticket ticket) throws Exception {
        // Validaciones
        if (ticket.getAsunto() == null || ticket.getAsunto().trim().isEmpty()) {
            throw new Exception("El asunto es obligatorio.");
        }
        if (ticket.getMensaje() == null || ticket.getMensaje().trim().isEmpty()) {
            throw new Exception("El mensaje no puede estar vacío.");
        }

        // Guardar en Base de Datos
        repositorio.guardar(ticket);

        // REQUERIMIENTO: Envío automatizado de correos
        simularEnvioCorreo(ticket);
    }

    public List<Ticket> obtenerTicketsDeUsuario(int idUsuario) {
        return repositorio.listarPorUsuario(idUsuario);
    }

    private void simularEnvioCorreo(Ticket t) {
        // Simulación del sistema de notificaciones
        System.out.println(">> [SISTEMA] Enviando correo de confirmación al usuario...");
        System.out.println(">> [EMAIL] Asunto: Recibo de Ticket #" + t.getAsunto());
        System.out.println(">> [EMAIL] Estado: Su solicitud ha sido registrada como PENDIENTE.");
    }
}