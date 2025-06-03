<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Modulo" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessao = request.getSession();

    // Recupera videoAulas
    @SuppressWarnings("unchecked")
    List<VideoAula> videoAulas = (List<VideoAula>) request.getAttribute("videoAulas");
    if (videoAulas == null) {
        videoAulas = new ArrayList<>();
    }

    // Monta map de <idModulo → nomeModulo> 
    Map<Integer, String> modMap = new HashMap<>();
    @SuppressWarnings("unchecked")
    List<Modulo> modulos = (List<Modulo>) request.getAttribute("modulos");
    if (modulos != null) {
        for (Modulo m : modulos) {
            modMap.put(m.getId(), m.getTitulo());
        }
    }

    // Verificação de login
    Aluno aluno = (Aluno) sessao.getAttribute("usuarioLogado");
    if (aluno == null) {
         response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
         return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Vídeo Aulas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS e Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- CSS específico desta página -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/listarvideoaulaAluno.css">
</head>
<body>
    <!-- ===== HEADER DO ALUNO ===== -->
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-light me-2"
                    type="button"
                    data-bs-toggle="offcanvas"
                    data-bs-target="#menuLateral"
                    aria-controls="menuLateral"
                    aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="<%= request.getContextPath() %>/pages/homeAluno.jsp" class="navbar-brand text-white">
                <img src="<%= request.getContextPath() %>/static/images/logo.png"
                     alt="Logo da Educação Inclusiva"
                     height="65">
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
    <!-- ===== FIM HEADER ===== -->

    <div class="main-content-wrapper">
        <h1 class="page-title">Vídeo Aulas Disponíveis</h1>

        <% if (videoAulas.isEmpty()) { %>
            <p class="subtext">Não há vídeo aulas para exibir.</p>
        <% } else { %>
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">Título</th>
                            <th scope="col">Link</th>
                            <th scope="col">Módulo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (VideoAula va : videoAulas) {
                               String nomeModulo = modMap.getOrDefault(
                                   va.getIdModulo(),
                                   "Módulo " + va.getIdModulo()
                               );
                        %>
                        <tr>
                            <td><strong><%= va.getTitulo() %></strong></td>
                            <td>
                                <a href="<%= va.getUrl() %>" target="_blank">
                                    <i class="bi bi-youtube me-1"></i><%= va.getUrl() %>
                                </a>
                            </td>
                            <td><%= nomeModulo %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } %>

        <div class="nav-buttons">
            <a href="videoaulaAluno.jsp" class="btn btn-secondary">
                <i class="bi bi-arrow-left-circle me-1"></i> Voltar ao Painel
            </a>
            <a href="perfilaluno.jsp" class="btn btn-secondary">
                <i class="bi bi-person-circle me-1"></i> Voltar ao Perfil
            </a>
        </div>
    </div>

    <!-- Bootstrap JS (necessário para offcanvas) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
