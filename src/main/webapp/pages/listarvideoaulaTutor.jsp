<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Usuario" %>

<%
    //Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    //if (usuario == null) {
        //response.sendRedirect("login.jsp");
        //return;
    //}

    // BLOCO DE TESTE (REMOVER)
    if (request.getAttribute("videoAulas") == null) {
        List<VideoAula> teste = new ArrayList<>();
        teste.add(new VideoAula(1, "Aula Exemplo 1", "https://www.youtube.com/embed/exemplo1", 101));
        teste.add(new VideoAula(2, "Aula Exemplo 2", "https://www.youtube.com/embed/exemplo2", 102));
        teste.add(new VideoAula(3, "Aula Exemplo 3", "https://www.youtube.com/embed/exemplo3", 103));
        request.setAttribute("videoAulas", teste);
    }
    // FIM DO BLOCO DE TESTE

    @SuppressWarnings("unchecked")
    List<VideoAula> videoAulas = (List<VideoAula>) request.getAttribute("videoAulas");
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Vídeo Aulas (Tutor)</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- CSS personalizado -->
    <link rel="stylesheet" href="<%= ctx %>/static/css/listarvideoaulaTutor.css">
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

    <!-- Conteúdo da página -->
    <div class="container mt-5">
        <h1 class="text-center mb-4">Listando vídeo aulas</h1>
        <div class="mb-3 text-end">
            <a class="btn btn-primary" href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp">
                <i class="bi bi-plus-circle me-2"></i> Cadastrar nova vídeo aula
            </a>
        </div>

        <% if (videoAulas == null || videoAulas.isEmpty()) { %>
            <p class="text-center">Você ainda não cadastrou nenhuma vídeo aula.</p>
        <% } else { %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>URL</th>
                        <th>Módulo</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (VideoAula va : videoAulas) { %>
                        <tr>
                            <td><%= va.getId() %></td>
                            <td><%= va.getTitulo() %></td>
                            <td>
                                <a href="<%= va.getUrl() %>" target="_blank">
                                    <i class="bi bi-box-arrow-up-right"></i> Ver
                                </a>
                            </td>
                            <td><%= va.getIdModulo() %></td>
                            <td>
                                <a class="btn btn-sm btn-warning" href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp?id=<%= va.getId() %>">
                                    <i class="bi bi-pencil"></i> Editar
                                </a>
                                <a class="btn btn-sm btn-danger" href="<%= ctx %>/videoaula?action=delete&id=<%= va.getId() %>"
                                   onclick="return confirm('Tem certeza que deseja excluir?');">
                                    <i class="bi bi-trash"></i> Excluir
                                </a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>

        <div class="mt-4">
            <a href="<%= ctx %>/pages/videoaulaTutor.jsp" class="btn btn-secondary me-2">
                <i class="bi bi-arrow-left"></i> Voltar ao painel de Vídeo Aula
            </a>
            <a href="<%= ctx %>/pages/perfiltutor.jsp" class="btn btn-secondary">
                <i class="bi bi-person-circle"></i> Voltar ao perfil de Tutor
            </a>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
