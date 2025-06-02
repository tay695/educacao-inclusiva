<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Tutor tutor = (Tutor) session.getAttribute("usuarioLogado");
    if (tutor == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Vídeo Aulas</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= ctx %>/static/css/videoaulaTutor.css">
</head>

<body>
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
                    <a class="nav-link" href="<%= ctx %>/pages/videoaulaTutor.jsp">
                        <i class="bi bi-camera-video me-2"></i> Nova aula
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= ctx %>/pages/listarvideoaulaTutor.jsp">
                        <i class="bi bi-folder-plus me-2"></i> Cursos postados
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

    <main class="container mt-5 main-content-wrapper">
        <h1 class="titulo-principal text-center mb-4">Painel de gerenciamento de aulas</h1>

        <div class="welcome-actions-group text-center">
            <h2 class="boas-vindas">
                Seja Bem-vindo, 
                <%= (tutor.getRetornaNome() != null ? tutor.getRetornaNome() : "Nome não informado") %>!
            </h2>
            <p class="especializacao">
                Área de especialização: 
                <%= (tutor.getAreaEspecializacao() != null ? tutor.getAreaEspecializacao() : "Não informada") %>
            </p>

            <h3 class="mt-4 mb-3">Ações rápidas:</h3>
            <div class="d-grid gap-3 col-md-8 mx-auto">
                <a href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp" class="btn btn-primary btn-lg custom-action-btn">
                    Postar nova vídeo aula
                </a>
                <a href="<%= ctx %>/pages/listarvideoaulaTutor.jsp" class="btn btn-info btn-lg custom-action-btn">
                    Visualizar todas as vídeo aulas
                </a>
                <a href="<%= ctx %>/pages/perfiltutor.jsp" class="btn btn-danger btn-lg custom-action-btn">
                    Retornar ao perfil
                </a>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
