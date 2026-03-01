package org.example.Servicio;

import org.example.Modelo.Usuario;
import org.example.Repositorio.UsuarioRepositorio;
import java.util.Optional;

public class UsuarioServicio {
    private final UsuarioRepositorio repositorio;

    public UsuarioServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Usuario usuario) throws Exception {
        if (repositorio.buscarPorEmail(usuario.getEmail()).isPresent()) {
            throw new Exception("Error: El correo ya está registrado.");
        }
        if (usuario.getPassword().length() < 5) {
            throw new Exception("Error: La contraseña es muy corta.");
        }
        repositorio.guardar(usuario);
    }

    public Usuario login(String email, String password) throws Exception {
        Optional<Usuario> usuarioOpt = repositorio.buscarPorEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new Exception("Error: Usuario no encontrado.");
        }

        Usuario usuario = usuarioOpt.get();
        // En un caso real, aquí deberías comparar hashes, no texto plano
        if (!usuario.getPassword().equals(password)) {
            throw new Exception("Error: Contraseña incorrecta.");
        }
        return usuario;
    }
}