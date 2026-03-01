package org.example.Vista;

import java.util.Scanner;

public class UsuarioVista {
    private final Scanner scanner;

    public UsuarioVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuBienvenida() {
        System.out.println("\n===== BIENVENIDO AL SISTEMA DE COMPRAS =====");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("0. Salir del sistema");
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // --- Formularios de Captura ---

    public CredencialesInput pedirCredenciales() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        return new CredencialesInput(email, password);
    }

    public RegistroInput pedirDatosRegistro() {
        System.out.println("\n--- Crear Nueva Cuenta ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        // Por defecto asignamos rol CLIENTE en el registro básico
        return new RegistroInput(nombre, email, password, "CLIENTE");
    }

    // --- Mensajes ---

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.err.println("❌ " + mensaje);
    }

    // --- DTOs Internos para transporte de datos de formulario ---

    public static class CredencialesInput {
        public String email;
        public String password;

        public CredencialesInput(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class RegistroInput {
        public String nombre;
        public String email;
        public String password;
        public String rol;

        public RegistroInput(String nombre, String email, String password, String rol) {
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.rol = rol;
        }
    }
}