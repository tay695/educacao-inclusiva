<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<% 
Tutor tutor = (Tutor) session.getAttribute("tutor");
String ctx = request.getContextPath();

if (tutor == null) {
    // Redireciona para o login se o tutor não estiver autenticado
    response.sendRedirect(ctx + "/pages/login.jsp");
    return; // Evita continuar processando a página
}
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Gerenciamento de Aulas</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= ctx %>/static/css/videoaulaTutor.css">
</head>

<body>
    <%-- HEADER --%>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="<%= ctx %>/pages/homeTutor.jsp" class="navbar-brand">
                <img src="<%= ctx %>/static/images/logo.png" alt="Logo da Educação Inclusiva" height="65">
            </a>
        </div>
    </nav>

    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar menu"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="<%= ctx %>/pages/perfiltutor.jsp">
                        <i class="bi bi-person-circle me-2"></i> Meu Perfil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp">
                        <i class="bi bi-camera-video me-2"></i> Nova aula
                    </a>
                </li>
                <li><hr></li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<%= ctx %>/controller/Logout">
                        <i class="bi bi-box-arrow-right me-2"></i> Sair
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <%-- FIM DO HEADER --%>

    <%-- CONTEÚDO PRINCIPAL --%>
    <div class="container mt-5">

        <h1 class="main-title text-center mb-4">Painel de gerenciamento de aulas</h1>

        <div class="content-wrapper text-center"> 
            <h2 class="welcome-message">Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h2>
            <p class="specialization-text">Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

            <h3 class="quick-actions-heading mt-4 mb-3">Ações rápidas:</h3>
            <div class="d-grid gap-3 col-md-8 mx-auto"> 
                <a href="formularioCadastroVideoAula.jsp" class="btn btn-primary btn-lg">
                    <i class="bi bi-camera-video"></i> Postar nova vídeo aula
                </a>
                <a href="listarvideoaulaTutor.jsp" class="btn btn-outline-info btn-lg">
                    <i class="bi bi-box-arrow-right"></i> Visualizar todas as vídeo aulas
                </a>
                <a href="perfiltutor.jsp" class="btn btn-outline-danger btn-lg">
                    <i class="bi bi-person-circle"></i> Retornar ao perfil
                </a>
            </div>
        </div>
    </div>
    <%-- FIM DO CONTEÚDO PRINCIPAL --%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
