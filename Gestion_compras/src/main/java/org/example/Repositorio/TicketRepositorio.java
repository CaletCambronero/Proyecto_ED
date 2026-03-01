package org.example.Repositorio;

import org.example.Modelo.Ticket;
import org.example.misc.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRepositorio implements IRepositorio<Ticket> {

    @Override
    public void guardar(Ticket ticket) {
        String sql = "INSERT INTO tickets (id_usuario, asunto, mensaje, estado, fecha_creacion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticket.getIdUsuario());
            pstmt.setString(2, ticket.getAsunto());
            pstmt.setString(3, ticket.getMensaje());
            pstmt.setString(4, ticket.getEstado());
            pstmt.setTimestamp(5, Timestamp.valueOf(ticket.getFechaCreacion()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método especializado para obtener solo los tickets del usuario logueado
    public List<Ticket> listarPorUsuario(int idUsuario) {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE id_usuario = ? ORDER BY fecha_creacion DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearTicket(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Ticket> listarTodos() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY fecha_creacion DESC";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearTicket(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Ticket> buscarPorId(int id) {
        // Implementación básica si se requiere detalle
        return Optional.empty();
    }

    @Override
    public void actualizar(Ticket entidad) {
        // Implementar si se desea cambiar estado a "RESUELTO"
    }

    @Override
    public void eliminar(int id) { }

    private Ticket mapearTicket(ResultSet rs) throws SQLException {
        return new Ticket(
                rs.getInt("id"),
                rs.getInt("id_usuario"),
                rs.getString("asunto"),
                rs.getString("mensaje"),
                rs.getString("estado"),
                rs.getTimestamp("fecha_creacion").toLocalDateTime()
        );
    }
}