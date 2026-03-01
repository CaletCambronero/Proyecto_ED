package org.example.Servicio;

import org.example.Modelo.Supermercado;
import org.example.Repositorio.SupermercadoRepositorio;
import java.util.List;
import java.util.Optional;

public class SupermercadoServicio {
    private final SupermercadoRepositorio repositorio;

    public SupermercadoServicio(SupermercadoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Supermercado superm) throws Exception {
        if (superm.getNombre() == null || superm.getNombre().trim().isEmpty()) {
            throw new Exception("Error: El nombre del supermercado es obligatorio.");
        }
        repositorio.guardar(superm);
    }

    public List<Supermercado> listar() {
        return repositorio.listarTodos();
    }

    public Supermercado buscarPorId(int id) throws Exception {
        Optional<Supermercado> opt = repositorio.buscarPorId(id);
        if (opt.isEmpty()) {
            throw new Exception("Supermercado no encontrado.");
        }
        return opt.get();
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}