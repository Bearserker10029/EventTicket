<%@ page import="java.util.ArrayList" %>
    <%@ page import="com.example.lab10_iweb_20202132.Dto.listaticketsDTO" %>
        <%@ page contentType="text/html" pageEncoding="UTF-8" %>
            <jsp:useBean id="listatickets"
                type="java.util.ArrayList<com.example.lab10_iweb_20202132.Dto.listaticketsDTO>" scope="request" />

            <!DOCTYPE html>
            <html>

            <head>
                <title>Lista de Tickets</title>
                <jsp:include page="../includes/headCss.jsp"></jsp:include>
            </head>

            <body>
                <div class='container'>
                    <jsp:include page="../includes/navbar.jsp">
                        <jsp:param name="currentPage" value="ticket" />
                    </jsp:include>

                    <div class="row mb-5 mt-4">
                        <div class="col-md-7">
                            <h1>Lista de Tickets</h1>
                        </div>
                        <div class="col-md-5 text-end">
                            <a href="<%= request.getContextPath()%>/TicketServlet?action=crear"
                                class="btn btn-success">Crear Tipo de Ticket</a>
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
                                                <th>Evento</th>
                                                <th>Descripción</th>
                                                <th>Fecha</th>
                                                <th>Lugar</th>
                                                <th>Tipo Ticket</th>
                                                <th>Precio</th>
                                                <th>Cupo Disponible</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (listaticketsDTO ticket : listatickets) { %>
                                                <tr>
                                                    <td>
                                                        <%= ticket.getTitulo() %>
                                                    </td>
                                                    <td>
                                                        <%= ticket.getDescripcion() !=null ? ticket.getDescripcion() : "" %>
                                                    </td>
                                                    <td>
                                                        <%= ticket.getFecha() %>
                                                    </td>
                                                    <td>
                                                        <%= ticket.getLugar() %>
                                                    </td>
                                                    <td>
                                                        <%= ticket.getNombre()   !=null ? ticket.getNombre() : "No disponible"  %>
                                                    </td>
                                                    <td>S/. <%= ticket.getPrecio()  !=null ? ticket.getPrecio() : "0" %>
                                                    </td>
                                                    <td>
                                                        <%= ticket.getCupo() %>
                                                    </td>
                                                    <td>
                                                        <form action="<%= request.getContextPath() %>/TicketServlet" method="post">
                                                            <input type="hidden" name="action" value="eliminar">
                                                            <input type="hidden" name="id" value="<%= ticket.getId_ticket_tipo() %>">
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