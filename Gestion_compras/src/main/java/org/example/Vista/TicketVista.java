package org.example.Vista;

import java.util.Scanner;

public class TicketVista {
    private final Scanner scanner;

    public TicketVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== MÓDULO DE SOPORTE TÉCNICO =====");
        System.out.println("1. Crear nuevo Ticket de Soporte");
        System.out.println("2. Ver mis Tickets realizados");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public TicketInput pedirDatosTicket() {
        System.out.println("\n--- Nuevo Ticket ---");
        System.out.print("Asunto: ");
        String asunto = scanner.nextLine();
        System.out.print("Mensaje detallado: ");
        String mensaje = scanner.nextLine();
        return new TicketInput(asunto, mensaje);
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public static class TicketInput {
        public String asunto;
        public String mensaje;
        public TicketInput(String a, String m) {
            this.asunto = a; this.mensaje = m;
        }
    }
}