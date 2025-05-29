<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor"%>
<%
Tutor tutor = (Tutor) request.getAttribute("tutor");
%>
<html lang="pt-br">
<head>
<title>Perfil do Tutor</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" srel="stylesheet" crossorigin="anonymous">s
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>
<body>
<a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath} assets/components/header.jsp">

	<h2>
		Bem-vindo,
		<%=tutor.getRetornaNome()%>
		(Tutor)
	</h2>
	<p>
		Email:
		<%=tutor.getEmail()%></p>
	<p>
		Bio:
		<%=tutor.getBio()%></p>
	<p>
		Área de Especialização:
		<%=tutor.getAreaEspecializacao()%></p>
</body>
</html><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Painel do Tutor</title>
    
    <!-- Bootstrap e ícones -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/header.css">
</head>
<body>

    <!-- ✅ Cabeçalho com menu lateral -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>

            <a href="${pageContext.request.contextPath}/pages/homeTutor.jsp" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" height="55">
            </a>
        </div>
    </nav>

    <!-- ✅ Menu lateral (offcanvas) -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/pages/tutor/perfil.jsp">
                        <i class="bi bi-person-circle me-2"></i>Meu Perfil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/pages/tutor/nova-videoaula.jsp">
                        <i class="bi bi-camera-video me-2"></i>Adicionar Vídeo Aula
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/pages/tutor/novo-modulo.jsp">
                        <i class="bi bi-folder-plus me-2"></i>Novo Módulo
                    </a>
                </li>
                <li><hr></li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">
                        <i class="bi bi-box-arrow-right me-2"></i>Sair
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <!-- Conteúdo principal -->
    <div class="container mt-5">
        <h1>Bem-vindo, Tutor!</h1>
        <p>Aqui você pode gerenciar suas aulas e módulos.</p>
    </div>

    <!-- Scripts Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
