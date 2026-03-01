package org.example.Repositorio;

import org.example.Modelo.Producto;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepositorio implements IRepositorio<Producto> {

    @Override
    public void guardar(Producto p) {
        String sql = "INSERT INTO productos (nombre, marca, categoria) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getMarca());
            pstmt.setString(3, p.getCategoria());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("marca"), rs.getString("categoria")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Producto(
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("marca"), rs.getString("categoria")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public void actualizar(Producto p) { /* Implementar lógica UPDATE */ }
    @Override
    public void eliminar(int id) { /* Implementar lógica DELETE */ }
}