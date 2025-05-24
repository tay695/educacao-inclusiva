<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Capacita++</title>
      <!-- link do bootstrap -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
</head>
<body class="m-0 p-0">

<section class="hero d-flex align-items-center justify-content-center text-white text-center">
    <div class="container">
        <h1 class="display-5 fw-bold">Capacite-se para transformar realidades</h1>
        <p class="lead">Inclusão começa com conhecimento</p>
        <p class="mt-3">O Capacita+ oferece cursos e ferramentas para preparar profissionais que promovem a diversidade e a inclusão no ambiente de trabalho e na sociedade.</p>
        
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/webapp/pages/cadastro.jsp" class="btn btn-light btn-lg fw-bold me-2">REGISTRE-SE GRATUITAMENTE</a>
        </div>
        
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/webapp/pages/cadastro.jsp" class="btn btn-outline-light me-2">Registrar</a>
            <a href="${pageContext.request.contextPath}/webapp/pages/login.jsp" class="btn btn-outline-light">Iniciar Sessão</a>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
