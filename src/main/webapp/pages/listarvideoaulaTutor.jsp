<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    List<VideoAula> videoAulas = (List<VideoAula>) request.getAttribute("videoAulas");
    Tutor tutor = (Tutor) session.getAttribute("tutor");
    String ctx = request.getContextPath();
    
    if (tutor == null) {
        response.sendRedirect(ctx + "/pages/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minhas Videoaulas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .custom-navbar {
            background-color: #084673 !important;
        }
        .video-card {
            transition: transform 0.3s;
        }
        .video-card:hover {
            transform: scale(1.02);
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="<%= ctx %>/pages/dashboardTutor.jsp" class="navbar-brand">
                <img src="<%= ctx %>/static/images/logo.png" alt="Logo" height="65">
            </a>
        </div>
    </nav>

    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
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
                        <i class="bi bi-camera-video me-2"></i> Nova Aula
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<%= ctx %>/pages/listarvideoaulaTutor.jsp">
                        <i class="bi bi-collection-play me-2"></i> Minhas Aulas
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

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="bi bi-collection-play"></i> Minhas Videoaulas</h2>
            <a href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Adicionar Nova
            </a>
        </div>

        <% if (videoAulas != null && !videoAulas.isEmpty()) { %>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <% for (VideoAula video : videoAulas) { %>
                    <div class="col">
                        <div class="card h-100 video-card">
                            <div class="card-body">
                                <h5 class="card-title"><%= video.getTitulo() %></h5>
                                <div class="ratio ratio-16x9 mb-3">
                                    <iframe src="<%= video.getUrl().replace("watch?v=", "embed/") %>" 
                                            allowfullscreen></iframe>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <a href="<%= ctx %>/videoaula?action=edit&id=<%= video.getId() %>" 
                                       class="btn btn-sm btn-outline-primary">
                                        <i class="bi bi-pencil"></i> Editar
                                    </a>
                                    <a href="<%= ctx %>/videoaula?action=delete&id=<%= video.getId() %>" 
                                       class="btn btn-sm btn-outline-danger"
                                       onclick="return confirm('Tem certeza que deseja excluir esta videoaula?')">
                                        <i class="bi bi-trash"></i> Excluir
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <div class="alert alert-info">
                Nenhuma videoaula cadastrada ainda. 
                <a href="<%= ctx %>/pages/formularioCadastroVideoAula.jsp" class="alert-link">
                    Clique aqui para adicionar sua primeira videoaula.
                </a>
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>