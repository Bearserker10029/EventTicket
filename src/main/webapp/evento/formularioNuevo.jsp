<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Evento</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="evento"/>
    </jsp:include>

    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-8">
            <h1 class='mb-3'>Añadir Nuevo Evento</h1>
            <hr>
            
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="POST" action="EventoServlet?action=guardar">
                <div class="mb-3">
                    <label class="form-label" for="titulo">Título del Evento</label>
                    <input type="text" class="form-control form-control-sm" id="titulo" name="titulo" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="descripcion">Descripción</label>
                    <textarea class="form-control form-control-sm" id="descripcion" name="descripcion" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="fecha">Fecha del Evento</label>
                    <input type="date" class="form-control form-control-sm" id="fecha" name="fecha" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lugar">Lugar</label>
                    <input type="text" class="form-control form-control-sm" id="lugar" name="lugar" required>
                </div>
                <div class="mb-3">
                    <a href="<%= request.getContextPath()%>/EventoServlet" class="btn btn-danger">Cancelar</a>
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