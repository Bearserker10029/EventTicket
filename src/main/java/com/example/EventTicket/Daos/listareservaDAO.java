package com.example.lab10_iweb_20202132.Daos;

import com.example.lab10_iweb_20202132.Beans.reserva_item;
import com.example.lab10_iweb_20202132.Beans.ticket_tipo;
import com.example.lab10_iweb_20202132.Beans.usuario;
import com.example.lab10_iweb_20202132.Dto.listareservaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class listareservaDAO extends DaoBase {
    public ArrayList<listareservaDTO> listareserva() throws SQLException {
        ArrayList<listareservaDTO> lista = new ArrayList<>();
        String sql = "select r.id_item, e.titulo, e.fecha, u.nombres, u.apellidos, u.email, tt.nombre as nombre_ticket, r.cantidad"
                +
                " from reserva_item r " +
                " join usuario u ON r.id_usuario = u.id_usuario " +
                " join ticket_tipo tt ON r.id_ticket_tipo = tt.id_ticket_tipo " +
                " join evento e ON tt.id_evento = e.id_evento";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listareservaDTO reserva = new listareservaDTO();
                reserva.setId_item(rs.getInt("id_item"));
                reserva.setTitulo(rs.getString("titulo"));
                reserva.setFecha(rs.getDate("fecha"));
                reserva.setUsuario(rs.getString("nombres") + " " + rs.getString("apellidos"));
                reserva.setEmail(rs.getString("email"));
                reserva.setNombre(rs.getString("nombre_ticket"));
                reserva.setCantidad(rs.getInt("cantidad"));
                lista.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    public void crear(reserva_item reserva_item) throws SQLException {

        String sql = "insert into reserva_item (id_usuario, id_ticket_tipo, cantidad) values (?,?,?) on duplicate key update cantidad = cantidad + values(cantidad)";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setreservaparams(pstmt, reserva_item);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        String updateSql = "update ticket_tipo set cupo_disponible = cupo_disponible - ? where id_ticket_tipo = ?";
        try (Connection conn = this.getConection();
                PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setInt(1, reserva_item.getCantidad());
            updateStmt.setInt(2, reserva_item.getId_ticket_tipo().getId_ticket_tipo());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<usuario> listausuarios() throws SQLException {
        ArrayList<usuario> listausuarios = new ArrayList<>();
        String sql = "select * from usuario";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usuario usuario = new usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                listausuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listausuarios;
    }

    public ArrayList<ticket_tipo> listatickets_tipo() throws SQLException {
        ArrayList<ticket_tipo> listatickets = new ArrayList<>();
        String sql = "select * from ticket_tipo";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ticket_tipo ticket = new ticket_tipo();
                ticket.setId_ticket_tipo(rs.getInt("id_ticket_tipo"));
                ticket.setNombre(rs.getString("nombre"));
                listatickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listatickets;
    }

    private void setreservaparams(PreparedStatement pstmt, reserva_item reserva_item) throws SQLException {

        String Sql = "select tt.cupo_disponible, e.fecha from ticket_tipo tt " +
                "inner join evento e ON tt.id_evento = e.id_evento " +
                "where tt.id_ticket_tipo = ?";
        try (Connection conn = this.getConection();
                PreparedStatement ps = conn.prepareStatement(Sql)) {
            ps.setInt(1, reserva_item.getId_ticket_tipo().getId_ticket_tipo());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cupodisponible = rs.getInt("cupo_disponible");
                Date fechaevento = rs.getDate("fecha");
                java.util.Date fechaActual = new java.util.Date();

                if (fechaevento.before(fechaActual)) {
                    throw new IllegalArgumentException("La fecha del evento no es válida");
                }
                if (cupodisponible < reserva_item.getCantidad()) {
                    throw new IllegalArgumentException("La cantidad no es válida.");
                }
                if (cupodisponible <= 0) {
                    throw new IllegalArgumentException("No hay más cupos disponibles");
                }
            }
        }

        pstmt.setInt(1, reserva_item.getId_usuario().getId_usuario());
        pstmt.setInt(2, reserva_item.getId_ticket_tipo().getId_ticket_tipo());
        pstmt.setInt(3, reserva_item.getCantidad());

    }

    public void eliminarReserva(int idItem) throws SQLException {
        String updateSql = "update ticket_tipo tt " +
                "inner join reserva_item ri ON tt.id_ticket_tipo = ri.id_ticket_tipo " +
                "set tt.cupo_disponible = tt.cupo_disponible + ri.cantidad " +
                "where ri.id_item = ?";

        try (Connection conn = this.getConection();
                PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setInt(1, idItem);
            updateStmt.executeUpdate();
        }
        String sql = "delete from reserva_item where id_item = ?";
        try (Connection conn = this.getConection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idItem);
            pstmt.executeUpdate();
        }
    }
}
