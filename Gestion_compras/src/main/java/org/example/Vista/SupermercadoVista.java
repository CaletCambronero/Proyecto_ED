package org.example.Vista;

import java.util.Scanner;

public class SupermercadoVista {
    private final Scanner scanner;

    public SupermercadoVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE SUPERMERCADOS =====");
        System.out.println("1. Registrar Supermercado");
        System.out.println("2. Listar Supermercados");
        System.out.println("3. Eliminar Supermercado");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public SupermercadoInput pedirDatos() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();
        return new SupermercadoInput(nombre, ubicacion);
    }

    public int pedirId() {
        System.out.print("Ingrese ID del Supermercado: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    // DTO Interno para captura de datos
    public static class SupermercadoInput {
        public String nombre;
        public String ubicacion;

        public SupermercadoInput(String nombre, String ubicacion) {
            this.nombre = nombre;
            this.ubicacion = ubicacion;
        }
    }
}