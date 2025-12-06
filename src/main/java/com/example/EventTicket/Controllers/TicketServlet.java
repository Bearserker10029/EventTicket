package com.example.lab10_iweb_20202132.Controllers;

import com.example.lab10_iweb_20202132.Beans.evento;
import com.example.lab10_iweb_20202132.Beans.ticket_tipo;
import com.example.lab10_iweb_20202132.Daos.listaticketsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "TicketServlet", value = "/TicketServlet")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        listaticketsDAO listaticketsDAO = new listaticketsDAO();
        RequestDispatcher rd;
        switch (action) {
            case "lista":
                request.setAttribute("listatickets", listaticketsDAO.listatickets());
                rd = request.getRequestDispatcher("/ticket/lista.jsp");
                rd.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaevento", listaticketsDAO.listaevento());
                rd = request.getRequestDispatcher("/ticket/formularioNuevo.jsp");
                rd.forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        listaticketsDAO listaticketsDAO = new listaticketsDAO();

        switch (action) {
            case "guardar":
                try {
                    ticket_tipo ticket = new ticket_tipo();

                    evento evento = new evento();
                    evento.setId_evento(Integer.parseInt(request.getParameter("id_evento")));
                    ticket.setId_evento(evento);

                    ticket.setNombre(request.getParameter("nombre"));
                    ticket.setPrecio(new BigDecimal(request.getParameter("precio")));
                    ticket.setCupo_total(Integer.parseInt(request.getParameter("cupo_total")));
                    ticket.setCupo_disponible(Integer.parseInt(request.getParameter("cupo_disponible")));

                    listaticketsDAO.crearticket(ticket);
                    response.sendRedirect(request.getContextPath() + "/TicketServlet");
                } catch (SQLException | IllegalArgumentException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/TicketServlet?msg=Error+al+crear+el+ticket");
                }
                break;
            case "eliminar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    listaticketsDAO.borrarTicket(id);
                    response.sendRedirect(
                            request.getContextPath() + "/TicketServlet?msg=Evento+eliminado+exitosamente");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/TicketServlet");
                break;
        }
    }
}