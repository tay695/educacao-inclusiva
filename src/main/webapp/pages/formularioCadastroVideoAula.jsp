<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Modulo" %>
<%@ page import="java.util.List" %>

<%
    VideoAula video = (VideoAula) request.getAttribute("video");
    List<Modulo> modulos = (List<Modulo>) request.getAttribute("modulos");
    int idModuloSelecionado = (video != null) ? video.getIdModulo() : -1;
    String ctx = request.getContextPath();
%>  

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title><%= video == null ? "Cadastrar" : "Editar" %> vídeo-aula</title>

    <!-- Importações de fontes e ícones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="<%= ctx %>/static/css/videoaulaTutor.css">
    <link rel="stylesheet" href="<%= ctx %>/static/css/formularioCadastroVideoAula.css">
</head>

<body>
    <!-- Header com Navbar -->
    <nav class="navbar navbar-expand-lg custom-navbar bg-light">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="<%= ctx %>/pages/homeTutor.jsp" class="navbar-brand">
                <img src="<%= ctx %>/static/images/logo.png" alt="logo" height="65">
            </a>
        </div>
    </nav>

    <!-- Menu lateral -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar"></button>
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
                        <i class="bi bi-camera-video me-2"></i> Nova vídeo-aula
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

    <!-- Conteúdo da página -->
    <div class="container mt-5">
        <h1 class="text-center mb-4"><%= video == null ? "Cadastrar" : "Editar" %> vídeo-aula</h1>

        <form action="<%= ctx %>/videoaula" method="post">
            <% if (video != null) { %>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= video.getId() %>">
            <% } else { %>
                <input type="hidden" name="action" value="create">
            <% } %>

            <div class="mb-3">
                <label for="titulo" class="form-label">Título:</label>
                <input type="text" class="form-control" name="titulo" id="titulo" required 
                       value="<%= video != null ? video.getTitulo() : "" %>">
            </div>

            <div class="mb-3">
                <label for="url" class="form-label">Link da vídeo-aula:</label>
                <input type="url" class="form-control" name="url" id="url" placeholder="https://..." required
                       value="<%= video != null ? video.getUrl() : "" %>">
            </div>

            <button type="submit" class="btn btn-primary" title="Salvar vídeo-aula">
                <i class="bi bi-save me-2"></i><%= video == null ? "Cadastrar" : "Salvar" %>
            </button>
        </form>

        <div class="mt-4">
            <a href="<%= ctx %>/pages/listarvideoaulaTutor.jsp" class="btn btn-secondary me-2" title="Voltar à listagem de vídeo-aulas">
                <i class="bi bi-arrow-left"></i> Voltar à listagem de vídeo-aulas
            </a>
            <a href="<%= ctx %>/pages/videoaulaTutor.jsp" class="btn btn-secondary" title="Voltar ao painel de vídeo-aula">
                <i class="bi bi-camera-video"></i> Voltar ao Painel de Vídeo-aula
            </a>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>