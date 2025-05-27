<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Capacita++</title>    
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
    
    
</head>
<body class="m-0 p-0">
<nav class="navbar navbar-expand-lg navbar-dark bg-custom px-4 py-2">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo Capacita++" height="55">
        </a>
        <div class="nav-buttons">
            <a href="${pageContext.request.contextPath}/pages/cadastro.jsp" class="btn btn-outline-light me-2">Registrar</a>
            <a href="${pageContext.request.contextPath}/pages/login.jsp" class="btn btn-outline-light">Iniciar Sessão</a>
        </div>
    </div>
</nav>

<section class="hero text-white">
    <div class="container d-flex justify-content-between align-items-center flex-wrap">

        <!-- Texto à esquerda -->
        <div class="hero-text col-md-4">
            <h1>Capacite-se para transformar realidades</h1>
            <p>Inclusão começa com conhecimento</p>
            <p>A nossa política é oferecer capacitação para preparar profissionais para <br>
             a diversidade e a inclusão no ambiente de trabalho e na sociedade.</p>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/pages/cadastro.jsp" class="btn btn-resg">REGISTRE-SE GRATUITAMENTE</a>
            </div>
        </div>

        <!-- Imagem no centro (homem) -->
        <div class="col-md-2 d-flex justify-content-center">
            <img src="${pageContext.request.contextPath}/static/images/homem.png" alt="Imagem homem" class="img-central img-fluid">
        </div>

        <div class="col-md-4 d-flex justify-content-center align-items-center">
            <img src="${pageContext.request.contextPath}/static/images/menina.png" alt="Inclusão digital" class="hero-img img-fluid">
        </div>
    </div>

    <div class="hero-have">
        <svg viewBox="0 0 500 150" preserveAspectRatio="none" class="wave-svg">
            <path d="M0.x0,49.98 C150.00,150.00 350.00,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
                  style="stroke: none; fill: #ffffff;"></path>
        </svg>
    </div>
</section>

   <div class="hero-have">
    <svg viewBox="0 0 500 150" preserveAspectRatio="none" class="wave-svg">
        <path d="M0.20,49.98 C150.00,150.00 350.00,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z" style="stroke: none; fill: #ffffff;"></path>
    </svg>
</div>

</section>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>