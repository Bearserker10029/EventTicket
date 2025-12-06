package com.example.lab10_iweb_20202132.Beans;

import java.math.BigDecimal;

public class ticket_tipo {
    private int id_ticket_tipo;
    private evento id_evento;
    private String nombre;
    private BigDecimal precio;
    private int cupo_total;
    private int cupo_disponible;

    public ticket_tipo() {
    }

    public ticket_tipo(int id_ticket_tipo, evento id_evento, String nombre, BigDecimal precio, int cupo_total, int cupo_disponible) {
        this.id_ticket_tipo = id_ticket_tipo;
        this.id_evento = id_evento;
        this.nombre = nombre;
        this.precio = precio;
        this.cupo_total = cupo_total;
        this.cupo_disponible = cupo_disponible;
    }

    public int getId_ticket_tipo() {
        return id_ticket_tipo;
    }

    public void setId_ticket_tipo(int id_ticket_tipo) {
        this.id_ticket_tipo = id_ticket_tipo;
    }

    public evento getId_evento() {
        return id_evento;
    }

    public void setId_evento(evento id_evento) {
        this.id_evento = id_evento;
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

    public int getCupo_total() {
        return cupo_total;
    }

    public void setCupo_total(int cupo_total) {
        this.cupo_total = cupo_total;
    }

    public int getCupo_disponible() {
        return cupo_disponible;
    }

    public void setCupo_disponible(int cupo_disponible) {
        this.cupo_disponible = cupo_disponible;
    }
}
