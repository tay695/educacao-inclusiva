<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/assets/components/header.jsp"%>
<!DOCTYPE html>
<html>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/homeTutor.css">

<body>
	<div class="container my-4">
		<div class="input-group">
			<input type="text" class="form-control"
				placeholder="Pesquisar cursos..." aria-label="Pesquisar cursos">
			<button class="btn btn-outline-secondary" type="button"
				id="button-search">
				<i class="bi bi-search"></i> Buscar
			</button>
		</div>
	</div>

	<a class="navbar-brand d-flex align-items-center gap-2"
		href="${pageContext.request.contextPath} assets/components/header.jsp">
		<span class="fw-bold text-white fs-4">CAPACITA++</span>
	</a>
	<div class="card" style="width: 18rem;">
		<img src="${pageContext.request.contextPath}/static/images/libras.png"
			class="card-img-top" alt="...">
		<div class="card-body">
			<h5 class="card-title">Curso de libras</h5>
			<p class="card-text">O curso está em andamento.</p>
		</div>
		<div>
    <ul class="button-add list-group-flush">
        <li class="list-group-item">
            <a href="${pageContext.request.contextPath}/pages/videoaulaTutor.jsp"
               class="btn btn-primary w-100">
                <i class="bi bi-camera-video-fill"></i> Gerenciar curso
            </a>
        </li>
    </ul>
</div>
</body>
</html>