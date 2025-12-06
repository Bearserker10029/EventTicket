package com.example.lab10_iweb_20202132.Beans;


import java.sql.Timestamp;

public class reserva_item {
    private int id_item;
    private usuario id_usuario;
    private ticket_tipo id_ticket_tipo;
    private int cantidad;
    private Timestamp agregado_en;

    public reserva_item() {
    }

    public reserva_item(int id_item, usuario id_usuario, ticket_tipo id_ticket_tipo, int cantidad, Timestamp agregado_en) {
        this.id_item = id_item;
        this.id_usuario = id_usuario;
        this.id_ticket_tipo = id_ticket_tipo;
        this.cantidad = cantidad;
        this.agregado_en = agregado_en;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public ticket_tipo getId_ticket_tipo() {
        return id_ticket_tipo;
    }

    public void setId_ticket_tipo(ticket_tipo id_ticket_tipo) {
        this.id_ticket_tipo = id_ticket_tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getAgregado_en() {
        return agregado_en;
    }

    public void setAgregado_en(Timestamp agregado_en) {
        this.agregado_en = agregado_en;
    }
}
