package org.example.Vista;

import java.util.Scanner;

public class CompraVista {
    private final Scanner scanner;

    public CompraVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n===== GESTIÓN DE COMPRAS =====");
        System.out.println("1. Registrar nueva compra");
        System.out.println("2. Ver mejor precio de un producto");
        System.out.println("3. Ver historial de precios");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Metodo genérico para mensajes
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Metodo específico para pedir datos de compra
    // Retorna un arreglo o un objeto DTO simple con los inputs
    public RegistroCompraInput pedirDatosCompra() {
        try {
            System.out.println("\n--- Nuevo Registro ---");
            System.out.print("ID del Producto: ");
            int idProd = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Supermercado: ");
            int idSuper = Integer.parseInt(scanner.nextLine());

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            return new RegistroCompraInput(idProd, idSuper, precio, cantidad);
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
            return null;
        }
    }

    public int pedirIdProducto() {
        System.out.print("Ingrese el ID del producto: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Clase interna simple para transportar los datos del formulario (Input DTO)
    public static class RegistroCompraInput {
        public int idProducto;
        public int idSupermercado;
        public double precio;
        public int cantidad;

        public RegistroCompraInput(int idProducto, int idSupermercado, double precio, int cantidad) {
            this.idProducto = idProducto;
            this.idSupermercado = idSupermercado;
            this.precio = precio;
            this.cantidad = cantidad;
        }
    }
}