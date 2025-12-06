<%@ page import="java.util.ArrayList" %>
    <%@ page import="com.example.EventTicket.Dto.listaeventoDTO" %>
        <%@ page contentType="text/html" pageEncoding="UTF-8" %>
            <% ArrayList<listaeventoDTO> listaevento = (ArrayList<listaeventoDTO>) request.getAttribute("listaevento");
                    if (listaevento == null) {
                    listaevento = new ArrayList<>();
                        }
                        %>

                        <!DOCTYPE html>
                        <html>

                        <head>
                            <title>Lista de Eventos</title>
                            <jsp:include page="../includes/headCss.jsp"></jsp:include>
                        </head>

                        <body>
                            <div class='container'>
                                <jsp:include page="../includes/navbar.jsp">
                                    <jsp:param name="currentPage" value="evento" />
                                </jsp:include>

                                <div class="row mb-5 mt-4">
                                    <div class="col-md-7">
                                        <h1>Lista de Eventos</h1>
                                    </div>
                                    <div class="col-md-5 text-end">
                                        <a href="<%= request.getContextPath()%>/EventoServlet?action=crear"
                                            class="btn btn-success">Añadir Evento</a>
                                    </div>
                                </div>

                                <% if (request.getParameter("msg") !=null) { %>
                                    <div class="alert alert-success" role="alert">
                                        <%= request.getParameter("msg") %>
                                    </div>
                                    <% } %>
                                        <% if (request.getParameter("err") !=null) { %>
                                            <div class="alert alert-danger" role="alert">
                                                <%= request.getParameter("err") %>
                                            </div>
                                            <% } %>

                                                <% if (listaevento.isEmpty()) { %>
                                                    <div class="alert alert-info" role="alert">
                                                        No hay eventos registrados.
                                                    </div>
                                                    <% } else { %>
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Título</th>
                                                                    <th>Descripción</th>
                                                                    <th>Fecha</th>
                                                                    <th>Lugar</th>
                                                                    <th>Acciones</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <% for (listaeventoDTO evento : listaevento) { %>
                                                                    <tr>
                                                                        <td>
                                                                            <%= evento.getTitulo() %>
                                                                        </td>
                                                                        <td>
                                                                            <%= evento.getDescripcion() !=null ?
                                                                                evento.getDescripcion() : "" %>
                                                                        </td>
                                                                        <td>
                                                                            <%= evento.getFecha() %>
                                                                        </td>
                                                                        <td>
                                                                            <%= evento.getLugar() %>
                                                                        </td>
                                                                        <td>
                                                                            <form action="<%= request.getContextPath() %>/EventoServlet" method="post">
                                                                                <input type="hidden" name="action" value="eliminar">
                                                                                <input type="hidden" name="id" value="<%= evento.getId_evento() %>">
                                                                                <button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
                                                                            </form>
                                                                        </td>
                                                                    </tr>
                                                                    <% } %>
                                                            </tbody>
                                                        </table>
                                                        <% } %>
                                                            <jsp:include page="../includes/footer.jsp" />
                            </div>
                        </body>

                        </html>