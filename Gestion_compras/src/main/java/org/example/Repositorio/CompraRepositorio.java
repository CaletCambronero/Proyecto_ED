package org.example.Repositorio;

import org.example.Dto.ComparacionPrecioDTO;
import org.example.Modelo.Compra;
import org.example.misc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompraRepositorio implements IRepositorio<Compra> {

    @Override
    public void guardar(Compra compra) {
        String sql = "INSERT INTO compras (id_usuario, id_producto, id_supermercado, precio, cantidad, fecha_compra) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, compra.getIdUsuario());
            pstmt.setInt(2, compra.getIdProducto());
            pstmt.setInt(3, compra.getIdSupermercado());
            pstmt.setDouble(4, compra.getPrecio());
            pstmt.setInt(5, compra.getCantidad());
            // Conversión necesaria de LocalDate a java.sql.Date
            pstmt.setDate(6, Date.valueOf(compra.getFechaCompra()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // En un entorno real, aquí podrías loguear el error
        }
    }

    /**
     * Metodo especializado para el requisito de "Mejor Opción".
     * Realiza un JOIN con la tabla supermercados para devolver el nombre del lugar y el precio.
     */
    public Optional<ComparacionPrecioDTO> obtenerMejorOpcion(int idProducto) {
        // Seleccionamos el nombre del súper, el precio y la fecha.
        // Ordenamos por precio ASC (menor a mayor) y limitamos a 1.
        String sql = """
                SELECT s.nombre, c.precio, c.fecha_compra 
                FROM compras c 
                JOIN supermercados s ON c.id_supermercado = s.id 
                WHERE c.id_producto = ? 
                ORDER BY c.precio ASC 
                LIMIT 1
                """;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Mapeamos el resultado al DTO para que el Servicio lo pueda usar
                return Optional.of(new ComparacionPrecioDTO(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDate("fecha_compra").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Obtiene todo el historial de precios para un producto específico,
     * útil para ver la variación de precios entre diferentes supermercados.
     */
    public List<ComparacionPrecioDTO> obtenerHistorialPrecios(int idProducto) {
        List<ComparacionPrecioDTO> historial = new ArrayList<>();
        String sql = """
                SELECT s.nombre, c.precio, c.fecha_compra 
                FROM compras c 
                JOIN supermercados s ON c.id_supermercado = s.id 
                WHERE c.id_producto = ? 
                ORDER BY c.precio ASC
                """;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                historial.add(new ComparacionPrecioDTO(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDate("fecha_compra").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historial;
    }

    // --- Métodos estándar de IRepositorio (Implementación básica) ---

    @Override
    public List<Compra> listarTodos() {
        // Se puede implementar si necesitas listar todas las compras sin filtro
        return new ArrayList<>();
    }

    @Override
    public Optional<Compra> buscarPorId(int id) {
        // Implementación básica si necesitas buscar una compra específica por su ID de transacción
        return Optional.empty();
    }

    @Override
    public void actualizar(Compra entidad) {
        // Implementación pendiente para "Editar Compra"
    }

    @Override
    public void eliminar(int id) {
        // Implementación pendiente para "Borrar Compra"
    }
}