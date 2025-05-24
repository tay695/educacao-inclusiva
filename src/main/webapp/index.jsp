<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Capacita++</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body class="m-0 p-0">

<header class="top-nav d-flex justify-content-end align-items-center px-4">
<nav class = "navbar navbar-expand-lg navbar-dark bg-custom px-4 py-2"></nav>
    <div class="nav-buttons">
        <a href="${pageContext.request.contextPath}../pages/cadastro.jsp" class="btn btn-light me-2 text-dark">Registrar</a>
        <a href="${pageContext.request.contextPath}../pages/login.jsp" class="btn btn-light text-dark">Iniciar Sessão</a>
    </div>
</header>

<section class="hero d-flex align-items-center justify-content-center text-white text-center">
    <div class="container hero-text">
        <h1>Capacite-se para transformar realidades</h1>
        <p>Inclusão começa com conhecimento</p>
        <p>O Capacita+ oferece cursos e ferramentas para preparar profissionais que promovem a diversidade e a inclusão no ambiente de trabalho e na sociedade.</p>

        <div class="mt-4 d-flex justify-content-center">
            <a href="${pageContext.request.contextPath}/webapp/pages/cadastro.jsp" class="btn btn-light btn-lg fw-bold">REGISTRE-SE GRATUITAMENTE</a>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
