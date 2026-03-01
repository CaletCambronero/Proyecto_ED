package org.example.Repositorio;

import org.example.Modelo.Supermercado;
import org.example.misc.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupermercadoRepositorio implements IRepositorio<Supermercado> {

    @Override
    public void guardar(Supermercado superm) {
        String sql = "INSERT INTO supermercados (nombre, ubicacion) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, superm.getNombre());
            pstmt.setString(2, superm.getUbicacion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supermercado> listarTodos() {
        List<Supermercado> lista = new ArrayList<>();
        String sql = "SELECT * FROM supermercados";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Supermercado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Supermercado> buscarPorId(int id) {
        String sql = "SELECT * FROM supermercados WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Supermercado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void actualizar(Supermercado superm) {
        String sql = "UPDATE supermercados SET nombre = ?, ubicacion = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, superm.getNombre());
            pstmt.setString(2, superm.getUbicacion());
            pstmt.setInt(3, superm.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM supermercados WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}