package org.example.Vista;

import java.util.Scanner;

public class ProductoVista {
    private final Scanner scanner;

    public ProductoVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Listar Productos");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public ProductoInput pedirDatos() {
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        return new ProductoInput(nombre, marca, categoria);
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public static class ProductoInput {
        public String nombre, marca, categoria;
        public ProductoInput(String n, String m, String c) {
            this.nombre = n; this.marca = m; this.categoria = c;
        }
    }
}