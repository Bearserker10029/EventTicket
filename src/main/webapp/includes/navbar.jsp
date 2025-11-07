<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <% String currentPage=request.getParameter("currentPage")==null ? "" : request.getParameter("currentPage"); %>

        <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/EventoServlet">Tienda</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link <%= currentPage.equals(" eventos") ? "active" : "" %>"
                                href="<%=request.getContextPath()%>/EventoServlet">
                                    Ver Eventos
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link <%= currentPage.equals(" tickets") ? "active" : "" %>"
                                href="<%=request.getContextPath()%>/TicketServlet">
                                    Ver Tickets
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link <%= currentPage.equals(" reserva") ? "active" : "" %>"
                                href="<%=request.getContextPath()%>/ReservaServlet">
                                    Ver Reserva
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>