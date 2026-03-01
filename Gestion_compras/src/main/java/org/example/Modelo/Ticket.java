package org.example.Modelo;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int idUsuario;
    private String asunto;
    private String mensaje;
    private String estado;
    private LocalDateTime fechaCreacion;

    public Ticket(int id, int idUsuario, String asunto, String mensaje, String estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}