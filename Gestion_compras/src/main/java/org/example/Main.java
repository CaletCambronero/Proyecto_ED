package org.example;

import org.example.Controlador.CompraControlador;
import org.example.Controlador.ProductoControlador;
import org.example.Controlador.SupermercadoControlador;
import org.example.Controlador.TicketControlador;
import org.example.Controlador.UsuarioControlador;
import org.example.Modelo.Usuario;
import org.example.Repositorio.CompraRepositorio;
import org.example.Repositorio.ProductoRepositorio;
import org.example.Repositorio.SupermercadoRepositorio;
import org.example.Repositorio.TicketRepositorio;
import org.example.Repositorio.UsuarioRepositorio;
import org.example.Servicio.CompraServicio;
import org.example.Servicio.ProductoServicio;
import org.example.Servicio.SupermercadoServicio;
import org.example.Servicio.TicketServicio;
import org.example.Servicio.UsuarioServicio;
import org.example.Vista.CompraVista;
import org.example.Vista.ProductoVista;
import org.example.Vista.SupermercadoVista;
import org.example.Vista.TicketVista;
import org.example.Vista.UsuarioVista;
import org.example.misc.Conexion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (Conexion.getConnection() == null) {
            System.out.println("❌ Error crítico: No hay conexión a la base de datos.");
            return;
        }

        // --- INICIALIZACIÓN DE USUARIOS ---
        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
        UsuarioServicio usuarioServicio = new UsuarioServicio(usuarioRepo);
        UsuarioVista usuarioVista = new UsuarioVista();
        UsuarioControlador usuarioControlador = new UsuarioControlador(usuarioServicio, usuarioVista);

        // --- LOGIN ---
        Usuario usuarioLogueado = usuarioControlador.autenticar();

        if (usuarioLogueado != null) {
            iniciarSistema(usuarioLogueado);
        } else {
            System.out.println("Aplicación finalizada.");
        }
    }

    private static void iniciarSistema(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        // --- INYECCIÓN DE DEPENDENCIAS ---

        // Módulos de Administración
        SupermercadoRepositorio superRepo = new SupermercadoRepositorio();
        SupermercadoServicio superServicio = new SupermercadoServicio(superRepo);
        SupermercadoVista superVista = new SupermercadoVista();
        SupermercadoControlador superControlador = new SupermercadoControlador(superServicio, superVista);

        ProductoRepositorio prodRepo = new ProductoRepositorio();
        ProductoServicio prodServicio = new ProductoServicio(prodRepo);
        ProductoVista prodVista = new ProductoVista();
        ProductoControlador prodControlador = new ProductoControlador(prodServicio, prodVista);

        // Módulos de Usuario Final
        CompraRepositorio compraRepo = new CompraRepositorio();
        CompraServicio compraServicio = new CompraServicio(compraRepo);
        CompraVista compraVista = new CompraVista();
        CompraControlador compraControlador = new CompraControlador(compraServicio, compraVista, usuario);

        TicketRepositorio ticketRepo = new TicketRepositorio();
        TicketServicio ticketServicio = new TicketServicio(ticketRepo);
        TicketVista ticketVista = new TicketVista();
        TicketControlador ticketControlador = new TicketControlador(ticketServicio, ticketVista, usuario);

        // --- MENÚ PRINCIPAL CON SEGURIDAD ---
        while (opcion != 0) {
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE COMPRAS");
            System.out.println("   Usuario: " + usuario.getNombre());
            System.out.println("   Rol: [" + usuario.getRol() + "]");
            System.out.println("=========================================");

            // Opciones visibles para TODOS
            System.out.println("1. Registrar y Comparar Compras");
            System.out.println("2. Soporte Técnico (Tickets)");

            // Opciones visibles SOLO para ADMIN
            if (usuario.getRol().equalsIgnoreCase("ADMIN")) {
                System.out.println("-----------------------------------------");
                System.out.println("3. GESTIÓN: Supermercados");
                System.out.println("4. GESTIÓN: Productos");
            }

            System.out.println("0. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");

            try {
                String input = scanner.nextLine();
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> compraControlador.iniciar();
                    case 2 -> ticketControlador.iniciar();

                    // Bloque de seguridad para opciones administrativas
                    case 3 -> {
                        if (usuario.getRol().equalsIgnoreCase("ADMIN")) {
                            superControlador.iniciar();
                        } else {
                            System.out.println("⛔ ACCESO DENEGADO: Requiere permisos de Administrador.");
                        }
                    }
                    case 4 -> {
                        if (usuario.getRol().equalsIgnoreCase("ADMIN")) {
                            prodControlador.iniciar();
                        } else {
                            System.out.println("⛔ ACCESO DENEGADO: Requiere permisos de Administrador.");
                        }
                    }

                    case 0 -> System.out.println("Cerrando sesión...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}