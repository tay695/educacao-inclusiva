<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${empty aulasDisponiveis}">
	<div class="alert alert-warning">Nenhuma aula disponível no
		momento.</div>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal estudante</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">

<style type="text/css">
body {
	font-family: "Segoe UI", sans-serif;
	background: white;
	color: #1e3c72;
	padding-top: 70px;
}

.navbar {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	z-index: 1030;
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.main-content {
	margin-top: 20px;
	padding: 15px;
}

h1 {
	font-size: 28px;
	margin-bottom: 10px;
}

h2 {
	font-size: 20px;
	margin: 25px 0 15px;
}

.offcanvas {
	top: 56px;
	height: calc(100vh - 56px) !important;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg custom-navbar">
		<div class="container-fluid">
			<button class="btn btn-outline-secondary me-2" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#menuLateral"
				aria-controls="menuLateral" aria-label="Abrir menu lateral">
				<i class="bi bi-list fs-3"></i>
			</button>
			<a href="${pageContext.request.contextPath}/pages/homeAluno.jsp"
				class="navbar-brand"> <img
				src="${pageContext.request.contextPath}/static/images/logo.png"
				alt="logo" height="65" />
			</a>
		</div>
	</nav>

	<div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral"
		aria-labelledby="menuLateralLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="menuLateralLabel">Menu Aluno</h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
				aria-label="Fechar"></button>
		</div>
		<div class="offcanvas-body">
			<ul class="nav flex-column">

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/pages/perfilaluno.jsp">
						<i class="bi bi-camera-video me-2"></i>Perfil
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/pages/listarvideoaulaAluno.jsp">
						<i class="bi bi-camera-video me-2"></i>Minhas aulas
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/pages/listarvideoaulaAluno.jsp">
						<i class="bi bi-folder-plus me-2"></i>Cursos inscritos
				</a></li>
				
				<li><hr /></li>
				<li class="nav-item"><a class="nav-link text-danger"
					href="${pageContext.request.contextPath}/index.jsp"> <i
						class="bi bi-box-arrow-right me-2"></i>Sair
				</a></li>
			</ul>
		</div>
	</div>
	<div class="container main-content">
		<h1> Disponíveis</h1>

		<c:if test="${not empty mensagem}">
			<div class="alert alert-info">${mensagem}</div>
		</c:if>
		<div class="row">
			<c:forEach items="${aulasDisponiveis}" var="aula">
				<div class="col-md-4 mb-4">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">${aula.titulo}</h5>
							<c:if test="${not empty aula.url}">
								<div class="ratio ratio-16x9 mb-3">
									<iframe
										src="https://www.youtube.com/embed/${fn:replace(aula.url, 'https://www.youtube.com/watch?v=', '')}"
										title="${aula.titulo}" allowfullscreen></iframe>
								</div>
							</c:if>
							<form action="${pageContext.request.contextPath}/inscreverAula"
								method="post">
								<input type="hidden" name="aulaId" value="${aula.id}" />
								<button type="submit" class="btn btn-inscrever w-100">
									<i class="bi bi-bookmark-plus"></i> Inscrever-se
								</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>