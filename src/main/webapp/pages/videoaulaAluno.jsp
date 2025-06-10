<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>

<%
    Aluno aluno = (Aluno) session.getAttribute("aluno");
    if (aluno == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Painel de Vídeo Aulas</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/videoaulaAluno.css">

</head>

<body>

<nav class="navbar navbar-expand-lg custom-navbar bg-primary">
    <div class="container-fluid">
        <button class="btn btn-outline-light me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
            <i class="bi bi-list fs-3"></i>
        </button>
        <a href="<%= request.getContextPath() %>/pages/homeAluno.jsp" class="navbar-brand text-white">
            <img src="<%= request.getContextPath() %>/static/images/logo.png" alt="Logo da Educação Inclusiva" height="65">
        </a>
    </div>
</nav>

<div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
    <div class="offcanvas-header bg-primary text-white">
        <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Aluno</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar menu"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="perfilaluno.jsp">
                    <i class="bi bi-person-circle me-2"></i> Meu Perfil
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listarvideoaulaAluno.jsp">
                    <i class="bi bi-camera-video me-2"></i> Ver Vídeo Aulas
                </a>
            </li>
            <li><hr></li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="<%= request.getContextPath() %>/controller/Logout">
                    <i class="bi bi-box-arrow-right me-2"></i> Sair
                </a>
            </li>
        </ul>
    </div>
</div>



    <h1 class="mt-4">Menu de visualização de vídeo aulas</h1>

    
    <h2>Seja Bem-vindo, <%= aluno.getRetornaNome() %>!</h2>
    <p>Email: <%= aluno.getEmail() %></p>

    <div class="d-grid gap-3 mt-3">
        <a href="<%= request.getContextPath() %>/controller/Logout" class="btn btn-outline-danger btn-lg">
            <i class="bi bi-box-arrow-right"></i> Sair
        </a>
    </div>

    <!-- Seção de listagem de vídeos -->
    <div class="mt-4">
        <h3><i class="bi bi-collection-play"></i> Minhas Vídeo Aulas</h3>
        
     
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
