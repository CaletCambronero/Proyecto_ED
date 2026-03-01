package org.example.Servicio;

import org.example.Modelo.Producto;
import org.example.Repositorio.ProductoRepositorio;
import java.util.List;

public class ProductoServicio {
    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Producto p) throws Exception {
        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio.");
        }
        repositorio.guardar(p);
    }

    public List<Producto> listar() {
        return repositorio.listarTodos();
    }

    public void eliminar(int id) {
        repositorio.eliminar(id); // Asegúrate de implementar eliminar en el repositorio
    }
}