package org.example.Repositorio;

import java.util.List;
import java.util.Optional;

public interface IRepositorio<T> {
    void guardar(T entidad);
    Optional<T> buscarPorId(int id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(int id);
}