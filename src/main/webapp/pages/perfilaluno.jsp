<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<% 
Aluno aluno = (Aluno) session.getAttribute("aluno");
String ctx = request.getContextPath();

if (aluno == null) {
    response.sendRedirect(ctx + "/pages/login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PainelPessoal</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="<%= ctx %>/static/css/perfilAluno.css">
    
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
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu Aluno</h5>
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
                    <a class="nav-link" href="<%= ctx %>/videoaula">
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
   
    <div class="container mt-5">
        <h1 class="main-title text-center mb-4">Painel de gerenciamento de aulas</h1>

        <div class="content-wrapper text-center"> 
            <h2 class="welcome-message">Seja Bem-vindo, <%= aluno.getRetornaNome() %>!</h2>

            <h3 class="quick-actions-heading mt-4 mb-3">Ações rápidas:</h3>
            <div class="d-grid gap-3 col-md-8 mx-auto"> 
            
                <a href="<%= ctx %>/pages/listarvideoaulaTutor.jsp" class="btn btn-outline-info btn-lg">
                    <i class="bi bi-collection-play"></i> Visualizar todas as vídeo aulas
                </a>
                <a href="<%= ctx %>/pages/dashboardAluno.jsp" class="btn btn-outline-danger btn-lg">
                    <i class="bi bi-person-circle"></i> Retornar ao inicio
                </a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('a[href*="/delete"]').forEach(link => {
                link.addEventListener('click', function(e) {
                    if (!confirm('Tem certeza que deseja excluir esta videoaula?')) {
                        e.preventDefault();
                    }
                });
            });
        });
    </script>
</body>
</html>