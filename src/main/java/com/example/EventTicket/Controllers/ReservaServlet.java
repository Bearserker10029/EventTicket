package com.example.lab10_iweb_20202132.Controllers;

import com.example.lab10_iweb_20202132.Beans.reserva_item;
import com.example.lab10_iweb_20202132.Beans.ticket_tipo;
import com.example.lab10_iweb_20202132.Beans.usuario;
import com.example.lab10_iweb_20202132.Daos.listareservaDAO;
import com.example.lab10_iweb_20202132.Dto.listareservaDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ReservaServlet", value = "/ReservaServlet")
public class ReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        listareservaDAO listareservaDAO = new listareservaDAO();
        RequestDispatcher rd;
        switch (action) {
            case "lista":
                try {
                    ArrayList<listareservaDTO> listareserva = listareservaDAO.listareserva();
                    request.setAttribute("listareserva", listareserva);
                    rd = request.getRequestDispatcher("reserva/lista.jsp");
                    rd.forward(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "crear":
                try {
                    ArrayList<ticket_tipo> listatickets_tipo = listareservaDAO.listatickets_tipo();

                    ArrayList<usuario> listausuarios = listareservaDAO.listausuarios();
                    request.setAttribute("listausuarios", listausuarios);
                    request.setAttribute("listatickets_tipo", listatickets_tipo);
                    rd = request.getRequestDispatcher("reserva/formularioNuevo.jsp");
                    rd.forward(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action) {
            case "guardar":
                try {
                    reserva_item reserva_item = new reserva_item();
                    int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
                    int idTicketTipo = Integer.parseInt(request.getParameter("id_ticket_tipo"));
                    int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                    usuario usuario = new usuario();
                    usuario.setId_usuario(idUsuario);
                    reserva_item.setId_usuario(usuario);

                    ticket_tipo ticket_tipo = new ticket_tipo();
                    ticket_tipo.setId_ticket_tipo(idTicketTipo);
                    reserva_item.setId_ticket_tipo(ticket_tipo);

                    reserva_item.setCantidad(cantidad);

                    listareservaDAO listareservaDAO = new listareservaDAO();
                    listareservaDAO.crear(reserva_item);

                    response.sendRedirect(request.getContextPath() + "/ReservaServlet?msg=Reserva creada exitosamente");
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/ReservaServlet?err=Error al crear la reserva");
                }
                break;
            case "eliminar":
                try {
                    int idItem = Integer.parseInt(request.getParameter("id"));
                    listareservaDAO listareservaDAO = new listareservaDAO();
                    listareservaDAO.eliminarReserva(idItem);
                    response.sendRedirect(
                            request.getContextPath() + "/ReservaServlet?msg=Reserva+eliminada+exitosamente");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/ReservaServlet");
                break;
        }
    }
}