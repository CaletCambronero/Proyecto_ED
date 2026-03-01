package org.example.Controlador;

import org.example.Modelo.Usuario;
import org.example.Servicio.UsuarioServicio;
import org.example.Vista.UsuarioVista;

public class UsuarioControlador {
    private final UsuarioServicio servicio;
    private final UsuarioVista vista;

    public UsuarioControlador(UsuarioServicio servicio, UsuarioVista vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    /**
     * Inicia el flujo de autenticación.
     * @return El objeto Usuario si el login es exitoso, o null si el usuario decide salir.
     */
    public Usuario autenticar() {
        int opcion = -1;
        Usuario usuarioLogueado = null;

        while (usuarioLogueado == null && opcion != 0) {
            opcion = vista.mostrarMenuBienvenida();
            switch (opcion) {
                case 1 -> usuarioLogueado = procesarLogin();
                case 2 -> procesarRegistro();
                case 0 -> vista.mostrarMensaje("Saliendo de la aplicación...");
                default -> vista.mostrarError("Opción no válida.");
            }
        }
        return usuarioLogueado;
    }

    private Usuario procesarLogin() {
        UsuarioVista.CredencialesInput credenciales = vista.pedirCredenciales();
        try {
            Usuario usuario = servicio.login(credenciales.email, credenciales.password);
            vista.mostrarExito("¡Bienvenido nuevamente, " + usuario.getNombre() + "!");
            return usuario;
        } catch (Exception e) {
            vista.mostrarError("Login fallido: " + e.getMessage());
            return null;
        }
    }

    private void procesarRegistro() {
        UsuarioVista.RegistroInput datos = vista.pedirDatosRegistro();
        try {
            // Convertimos el Input de la vista al Modelo del dominio
            Usuario nuevoUsuario = new Usuario(datos.nombre, datos.email, datos.password, datos.rol);

            servicio.registrar(nuevoUsuario);
            vista.mostrarExito("Usuario registrado correctamente. Por favor inicie sesión.");
        } catch (Exception e) {
            vista.mostrarError("Error en registro: " + e.getMessage());
        }
    }
}