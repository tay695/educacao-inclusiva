<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Portal do Tutor</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link href="${ctx}/static/css/tutor.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-light me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="${ctx}/pages/dashboardTutor.jsp" class="navbar-brand">
                <img src="${ctx}/static/images/logo.png" alt="logo" height="65">
            </a>
            <span class="navbar-text text-light ms-auto">
                <i class="bi bi-person-circle me-1"></i> ${sessionScope.tutor.retornaNome}
            </span>
        </div>
    </nav>

    <!-- Menu Lateral -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="${ctx}/pages/perfiltutor.jsp">
                        <i class="bi bi-person-circle me-2"></i> Meu Perfil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ctx}/pages/modulo.jsp">
                        <i class="bi bi-journal-plus me-2"></i> Novo Modulo
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ctx}/pages/formularioCadastroVideoAula.jsp">
                        <i class="bi bi-camera-video me-2"></i> Nova Aula
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ctx}/pages/listarvideoaulaTutor.jsp">
                        <i class="bi bi-collection-play me-2"></i> Minhas Aulas
                    </a>
                </li>
                <li><hr></li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="${ctx}/index.jsp">
                        <i class="bi bi-box-arrow-right me-2"></i> Sair
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <!-- Conteúdo Principal -->
    <div class="container mt-4">
        <!-- Mensagens de Feedback -->
        <c:if test="${not empty sessionScope.mensagem}">
            <div class="alert alert-success alert-dismissible fade show">
                ${sessionScope.mensagem}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <c:remove var="mensagem" scope="session"/>
        </c:if>

        <div class="row mb-4">
            <div class="col-md-12 text-center">
                <h1>Bem-vindo, ${sessionScope.tutor.retornaNome}!</h1>
                <p class="lead">Gerencie seus cursos e videoaulas</p>
            </div>
        </div>

        <!-- Cards de Ação Rápida -->
        <div class="row row-cols-1 row-cols-md-3 g-4 mb-4">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <i class="bi bi-journal-plus fs-1 text-primary mb-3"></i>
                        <h5 class="card-title">Novo MÓDULO</h5>
                        <p class="card-text">Crie um novo curso para seus alunos</p>
                        <a href="${ctx}/pages/modulo.jsp" class="btn btn-primary">Criar </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <i class="bi bi-camera-video fs-1 text-primary mb-3"></i>
                        <h5 class="card-title">Nova Videoaula</h5>
                        <p class="card-text">Adicione uma nova videoaula aos seus cursos</p>
                        <a href="${ctx}/pages/formularioCadastroVideoAula.jsp" class="btn btn-primary">Adicionar Aula</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <i class="bi bi-collection-play fs-1 text-primary mb-3"></i>
                        <h5 class="card-title">Gerenciar Aulas</h5>
                        <p class="card-text">Visualize e edite suas videoaulas</p>
                        <a href="${ctx}/pages/listarvideoaulaTutor.jsp" class="btn btn-primary">Ver Aulas</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Seção de Cursos Recentes -->
        <div class="row">
            <div class="col-md-12">
                <h3 class="mb-3"><i class="bi bi-journal-bookmark me-2"></i>Meus Cursos</h3>
                
                <c:choose>
                    <c:when test="${not empty cursos}">
                        <div class="row row-cols-1 row-cols-md-3 g-4">
                            <c:forEach items="${cursos}" var="curso">
                                <div class="col">
                                    <div class="card h-100">
                                        <img src="${ctx}/static/images/course-placeholder.jpg" class="card-img-top img-card" alt="${curso.titulo}">
                                        <div class="card-body">
                                            <h5 class="card-title">${curso.titulo}</h5>
                                            <p class="card-text text-muted">${curso.descricao}</p>
                                        </div>
                                        <div class="card-footer bg-transparent">
                                            <a href="${ctx}/modulo?idCurso=${curso.id}" class="btn btn-outline-primary btn-sm">
                                                Gerenciar Módulos
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info">
                            Você ainda não criou nenhum curso. 
                            <a href="${ctx}/pages/modulo.jsp" class="alert-link">Clique aqui para criar seu primeiro curso</a>.
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Fechar alertas automaticamente após 5 segundos
        window.setTimeout(function() {
            $(".alert").fadeTo(500, 0).slideUp(500, function(){
                $(this).remove(); 
            });
        }, 5000);
    </script>
</body>
</html>