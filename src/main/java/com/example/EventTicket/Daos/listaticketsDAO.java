package com.example.EventTicket.Daos;

import com.example.EventTicket.Beans.evento;
import com.example.EventTicket.Beans.ticket_tipo;
import com.example.EventTicket.Dto.listaticketsDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class listaticketsDAO extends DaoBase {
    public ArrayList<listaticketsDTO> listatickets() {
        ArrayList<listaticketsDTO> lista = new ArrayList<>();
        String sql = "select t.id_ticket_tipo, e.titulo, e.descripcion, e.fecha, e.lugar, t.nombre, t.precio, t.cupo_disponible from evento e"
                +
                " left join ticket_tipo t on e.id_evento = t.id_evento";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaticketsDTO producto = new listaticketsDTO();
                producto.setId_ticket_tipo(rs.getInt("id_ticket_tipo"));
                producto.setTitulo(rs.getString("titulo"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setFecha(rs.getDate("fecha"));
                producto.setLugar(rs.getString("lugar"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setCupo(rs.getInt("cupo_disponible"));
                lista.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void crearticket(ticket_tipo ticket_tipo)
            throws SQLException {

        String sql = "insert into ticket_tipo (id_evento, nombre, precio, cupo_total, cupo_disponible) values (?,?,?,?,?)";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setticketsparams(pstmt, ticket_tipo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<evento> listaevento() {
        ArrayList<evento> listaevento = new ArrayList<>();
        String sql = "select * from evento";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                evento evento = new evento();
                evento.setId_evento(rs.getInt("id_evento"));
                evento.setTitulo(rs.getString("titulo"));
                listaevento.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaevento;
    }

    public void borrarTicket(int id) throws SQLException {
        String sql = "DELETE from ticket_tipo where id_ticket_tipo = ?";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private void setticketsparams(PreparedStatement pstmt, ticket_tipo ticket_tipo) throws SQLException {
        BigDecimal precio = ticket_tipo.getPrecio();
        Integer cupo_total = ticket_tipo.getCupo_total();
        Integer cupo_disponible = ticket_tipo.getCupo_disponible();
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio es inválido");
        }
        if (cupo_total < 0) {
            throw new IllegalArgumentException("No hay cupos");
        }
        if (cupo_disponible < 0) {
            throw new IllegalArgumentException("No hay más cupos disponibles");

        }
        if (cupo_total < cupo_disponible) {
            throw new IllegalArgumentException("Inválido");
        }

        pstmt.setInt(1, ticket_tipo.getId_evento().getId_evento());
        pstmt.setString(2, ticket_tipo.getNombre());
        pstmt.setBigDecimal(3, precio);
        pstmt.setInt(4, cupo_total);
        pstmt.setInt(5, cupo_disponible);

    }
}
