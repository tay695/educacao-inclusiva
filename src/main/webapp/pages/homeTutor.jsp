<%@ page import="com.ifbaiano.educacaoinclusiva.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Página inicial - Tutor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/homeTutor.css" />
</head>
<body>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="${pageContext.request.contextPath}/pages/homeTutor.jsp" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" height="65" />
            </a>
        </div>
    </nav>
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/perfiltutor.jsp"><i class="bi bi-person-circle me-2"></i>Meu Perfil</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/videoaulaTutor.jsp"><i class="bi bi-camera-video me-2"></i>Nova aula</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/listarvideoaulaTutor.jsp"><i class="bi bi-folder-plus me-2"></i>Cursos postados</a></li>
                <li><hr /></li>
                <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/controller/Logout"><i class="bi bi-box-arrow-right me-2"></i>Sair</a></li>
            </ul>
        </div>
    </div>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Bem-vindo, <%= usuario.getRetornaNome() %>!</h1>

<%
    List<VideoAula> lista = (List<VideoAula>) request.getAttribute("listaVideoaulas");
%>

<div class="row justify-content-center">
    <% if (lista != null && !lista.isEmpty()) {
        for (VideoAula v : lista) { %>
            <div class="col-12 mb-4">
                <div class="card h-100">
                    <iframe class="card-img-top" src="<%= v.getUrl() %>" allowfullscreen></iframe>
                    <div class="card-body">
                        <h5 class="card-title"><%= v.getTitulo() %></h5>
                        <a href="${pageContext.request.contextPath}/pages/listarvideoaulaTutor.jsp" class="btn-card">Gerenciar curso</a>
                    </div>
                </div>
            </div>
    <%  }
    } else { %>
        <p class="text-center">Nenhuma videoaula cadastrada ainda.</p>
    <% } %>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
