<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

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
	padding-top: 70px; /* Espaço para o navbar fixo */
}

/* Navbar fixo no topo */
.navbar {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	z-index: 1030; /* Garante que fique acima de outros elementos */
	background-color: white; /* Cor de fundo para sobrepor conteúdo */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	/* Sombra para efeito de elevação */
}

/* Conteúdo principal */
.main-content {
	margin-top: 20px; /* Espaço adicional após o navbar */
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

/* Ajuste para o offcanvas */
.offcanvas {
	top: 56px; /* Altura do navbar */
	height: calc(100vh - 56px) !important;
}
</style>
</head>
<body>
	<!-- Navbar fixo -->
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

	<!-- Menu lateral -->
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
						<i class="bi bi-person-circle me-2"></i>Meu Perfil
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/pages/videoaulaAluno.jsp">
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

	<!-- Conteúdo principal -->
	<div class="container main-content">
		<div class="menu2">
			<h2>O que você deseja fazer?</h2>
			<div class="d-grid gap-2">
				<a href="#" class="btn btn-outline-primary">Explorar cursos
					disponíveis</a> <a href="#" class="btn btn-outline-secondary">Enviar
					mensagem para tutor</a> <a href="#" class="btn btn-outline-success">Deixa
					sua opinião com um comentário</a>
			</div>
		</div>

		<c:forEach var="curso" items="${cursosDisponiveis}">
			<div class="card mb-3">
				<div class="card-body">
					<h5 class="card-title">${curso.titulo}</h5>
					<p class="card-text">${curso.descricao}</p>
					<form method="post"
						action="${pageContext.request.contextPath}/inscricao">
						<input type="hidden" name="idCurso" value="${curso.id}" />
						<button type="submit" class="btn btn-primary">Inscrever-se</button>
					</form>
				</div>
			</div>
		</c:forEach>


		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>