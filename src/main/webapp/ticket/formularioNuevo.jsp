<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_iweb_20202132.Beans.evento" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean scope="request" id="listaevento" type="java.util.ArrayList<com.example.lab10_iweb_20202132.Beans.evento>"/>

<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Tipo de Ticket</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="ticket"/>
    </jsp:include>

    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-8">
            <h1 class='mb-3'>Crear Tipo de Ticket</h1>
            <hr>
            
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="POST" action="TicketServlet?action=guardar">
                <div class="mb-3">
                    <label class="form-label" for="id_evento">Evento</label>
                    <select name="id_evento" id="id_evento" class="form-select" required>
                        <option value="">-- Seleccionar Evento --</option>
                        <% for (evento e : listaevento) { %>
                        <option value="<%=e.getId_evento()%>"><%=e.getTitulo()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="nombre">Nombre del Ticket</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="precio">Precio (S/.)</label>
                    <input type="number" class="form-control form-control-sm" id="precio" name="precio" 
                           step="0.01" min="0" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="cupo_total">Cupo Total</label>
                    <input type="number" class="form-control form-control-sm" id="cupo_total" name="cupo_total" 
                           min="1" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="cupo_disponible">Cupo Disponible</label>
                    <input type="number" class="form-control form-control-sm" id="cupo_disponible" name="cupo_disponible" 
                           min="0" required>
                </div>
                <div class="mb-3">
                    <a href="<%= request.getContextPath()%>/TicketServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="guardar" class="btn btn-primary"/>
                </div>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>