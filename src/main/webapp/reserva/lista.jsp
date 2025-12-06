<%@ page import="java.util.ArrayList" %>
    <%@ page import="com.example.EventTicket.Dto.listareservaDTO" %>
        <%@ page contentType="text/html" pageEncoding="UTF-8" %>
            <% ArrayList<listareservaDTO> listareserva = (ArrayList<listareservaDTO>)
                    request.getAttribute("listareserva");
                    if (listareserva == null) {
                    response.sendRedirect(request.getContextPath() + "/ReservaServlet");
                    return;
                    }
                    %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <title>Lista de Reservas</title>
                        <jsp:include page="../includes/headCss.jsp"></jsp:include>
                    </head>

                    <body>
                        <div class='container'>
                            <jsp:include page="../includes/navbar.jsp">
                                <jsp:param name="currentPage" value="reserva" />
                            </jsp:include>

                            <div class="row mb-5 mt-4">
                                <div class="col-md-7">
                                    <h1>Lista de Reservas</h1>
                                </div>
                                <div class="col-md-5 text-end">
                                    <a href="<%= request.getContextPath()%>/ReservaServlet?action=crear"
                                        class="btn btn-success">Añadir Reserva</a>
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

                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Título del Evento</th>
                                                        <th>Fecha del Evento</th>
                                                        <th>Usuario</th>
                                                        <th>Email del Usuario</th>
                                                        <th>Nombre del Ticket</th>
                                                        <th>Cantidad</th>
                                                        <th>Acciones</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (listareservaDTO item : listareserva) { %>
                                                        <tr>
                                                            <td>
                                                                <%= item.getTitulo() %>
                                                            </td>
                                                            <td>
                                                                <%= item.getFecha() %>
                                                            </td>
                                                            <td>
                                                                <%= item.getUsuario() %>
                                                            </td>
                                                            <td>
                                                                <%= item.getEmail() %>
                                                            </td>
                                                            <td>
                                                                <%= item.getNombre() %>
                                                            </td>
                                                            <td>
                                                                <%= item.getCantidad() %>
                                                            </td>
                                                            <td>
                                                                <form action="<%= request.getContextPath() %>/ReservaServlet" method="post">
                                                                    <input type="hidden" name="action" value="eliminar">
                                                                    <input type="hidden" name="id" value="<%= item.getId_item() %>">
                                                                    <button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                        <% } %>
                                                </tbody>
                                            </table>

                                            <jsp:include page="../includes/footer.jsp" />
                        </div>
                    </body>

                    </html>