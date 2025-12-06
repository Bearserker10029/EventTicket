package com.example.EventTicket.Dto;

import java.math.BigDecimal;
import java.sql.Date;

public class listaticketsDTO {
    private int id_ticket_tipo;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String lugar;
    private String nombre;
    private BigDecimal precio;
    private int cupo;

    public listaticketsDTO() {
    }

    public int getId_ticket_tipo() {
        return id_ticket_tipo;
    }

    public void setId_ticket_tipo(int id_ticket_tipo) {
        this.id_ticket_tipo = id_ticket_tipo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}
