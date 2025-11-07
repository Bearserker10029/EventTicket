<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_iweb_20202132.Beans.usuario" %>
<%@ page import="com.example.lab10_iweb_20202132.Beans.ticket_tipo" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listausuarios" type="java.util.ArrayList<com.example.lab10_iweb_20202132.Beans.usuario>" scope="request"/>
<jsp:useBean id="listatickets_tipo" type="java.util.ArrayList<com.example.lab10_iweb_20202132.Beans.ticket_tipo>" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Añadir Reserva</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="reserva"/>
    </jsp:include>

    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-8">
            <h1 class='mb-3'>Añadir Reserva</h1>
            <hr>

            <form method="POST" action="ReservaServlet?action=guardar">
                <div class="mb-3">
                    <label class="form-label" for="id_usuario">Usuario</label>
                    <select name="id_usuario" id="id_usuario" class="form-select" required>
                        <option value="">-- Seleccionar Usuario --</option>
                        <% for (usuario user : listausuarios) { %>
                        <option value="<%=user.getId_usuario()%>">
                            <%=user.getNombres()%> <%=user.getApellidos()%> - <%=user.getEmail()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="id_ticket_tipo">Tipo de Ticket</label>
                    <select name="id_ticket_tipo" id="id_ticket_tipo" class="form-select" required>
                        <option value="">-- Seleccionar Tipo de Ticket --</option>
                        <% for (ticket_tipo ticket : listatickets_tipo) { %>
                        <option value="<%=ticket.getId_ticket_tipo()%>"><%=ticket.getNombre()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="cantidad">Cantidad</label>
                    <input type="number" class="form-control form-control-sm" id="cantidad" name="cantidad" 
                           min="1" required>
                </div>
                <div class="mb-3">
                    <a href="<%= request.getContextPath()%>/ReservaServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Guardar" class="btn btn-primary"/>
                </div>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>