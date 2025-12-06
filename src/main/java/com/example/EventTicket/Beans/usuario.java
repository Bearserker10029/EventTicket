package com.example.EventTicket.Beans;

import java.sql.Timestamp;

public class usuario {
    private int id_usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private Timestamp creado_en;
    private Timestamp actualizado_en;

    public usuario() {
    }

    public usuario(int id_usuario, String nombres, String apellidos, String email, Timestamp creado_en, Timestamp actualizado_en) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.creado_en = creado_en;
        this.actualizado_en = actualizado_en;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreado_en() {
        return creado_en;
    }

    public void setCreado_en(Timestamp creado_en) {
        this.creado_en = creado_en;
    }

    public Timestamp getActualizado_en() {
        return actualizado_en;
    }

    public void setActualizado_en(Timestamp actualizado_en) {
        this.actualizado_en = actualizado_en;
    }
}
