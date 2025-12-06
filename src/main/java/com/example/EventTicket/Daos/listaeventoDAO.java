package com.example.lab10_iweb_20202132.Daos;

import com.example.lab10_iweb_20202132.Beans.evento;
import com.example.lab10_iweb_20202132.Dto.listaeventoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class listaeventoDAO extends DaoBase {
    public ArrayList<listaeventoDTO> listaevento() {
        ArrayList<listaeventoDTO> lista = new ArrayList<>();
        String sql = "select id_evento, titulo, descripcion, fecha, lugar from evento";

        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                listaeventoDTO evento = new listaeventoDTO();
                evento.setId_evento(rs.getInt("id_evento"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setFecha(rs.getDate("fecha"));
                evento.setLugar(rs.getString("lugar"));
                lista.add(evento);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void anadirevento(evento evento) throws SQLException {
        String sql = "INSERT INTO evento (titulo, descripcion, fecha, lugar) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            seteventoparams(pstmt, evento);
            pstmt.executeUpdate();
        }
    }

    private void seteventoparams(PreparedStatement pstmt, evento evento) throws SQLException {
        java.util.Date fechaActual = new java.util.Date();
        Date fechaEvento = new Date(evento.getFecha().getTime());

        if (fechaEvento.before(fechaActual)) {
            throw new IllegalArgumentException("La fecha del evento no es valida");
        }

        pstmt.setString(1, evento.getTitulo());
        pstmt.setString(2, evento.getDescripcion());
        pstmt.setDate(3, evento.getFecha());
        pstmt.setString(4, evento.getLugar());

    }

    public void eliminarEvento(int idEvento) throws SQLException {
        String sql = "DELETE from evento where id_evento = ?";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEvento);
            pstmt.executeUpdate();
        }
    }
}