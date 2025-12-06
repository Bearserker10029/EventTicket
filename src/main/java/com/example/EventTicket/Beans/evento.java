package com.example.EventTicket.Beans;

import java.sql.Timestamp;
import java.sql.Date;

public class evento {
    private int id_evento;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String lugar;
    private Timestamp creado_en;

    public evento() {
    }

    public evento(int id_evento, String titulo, String descripcion, Date fecha, String lugar, Timestamp creado_en) {
        this.id_evento = id_evento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.lugar = lugar;
        this.creado_en = creado_en;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Timestamp getCreado_en() {
        return creado_en;
    }

    public void setCreado_en(Timestamp creado_en) {
        this.creado_en = creado_en;
    }
}
