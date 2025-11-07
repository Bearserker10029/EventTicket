package com.example.lab10_iweb_20202132.Controllers;

import com.example.lab10_iweb_20202132.Beans.evento;
import com.example.lab10_iweb_20202132.Daos.listaeventoDAO;
import com.example.lab10_iweb_20202132.Dto.listaeventoDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EventoServlet", value = "/EventoServlet")
public class EventoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        listaeventoDAO listaeventoDAO = new listaeventoDAO();
        RequestDispatcher rd;

        switch (action) {
            case "crear":
                request.setAttribute("evento", new evento());
                rd = request.getRequestDispatcher("/evento/formularioNuevo.jsp");
                rd.forward(request, response);
                break;
            default:
                ArrayList<listaeventoDTO> listaevento = listaeventoDAO.listaevento();
                request.setAttribute("listaevento", listaevento);
                rd = request.getRequestDispatcher("/evento/lista.jsp");
                rd.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        listaeventoDAO listaeventoDAO = new listaeventoDAO();

        switch (action) {
            case "guardar":
                try {
                    evento evento = new evento();
                    evento.setTitulo(request.getParameter("titulo"));
                    evento.setDescripcion(request.getParameter("descripcion"));
                    evento.setFecha(Date.valueOf(request.getParameter("fecha")));
                    evento.setLugar(request.getParameter("lugar"));

                    listaeventoDAO.anadirevento(evento);
                    response.sendRedirect(request.getContextPath() + "/EventoServlet?msg=Evento creado exitosamente");
                } catch (SQLException | IllegalArgumentException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/EventoServlet?err=Error al crear el evento");
                }
                break;
            case "eliminar":
                try {
                    int idEvento = Integer.parseInt(request.getParameter("id"));
                    listaeventoDAO.eliminarEvento(idEvento);
                    response.sendRedirect(
                            request.getContextPath() + "/EventoServlet?msg=Evento+eliminado+exitosamente");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EventoServlet");
                break;
        }
    }
}
