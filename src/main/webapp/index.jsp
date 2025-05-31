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
            <a href="${pageContext.request.contextPath}/pages/cadastroAluno.jsp" class="btn btn-outline-light me-2">Entre como estudante</a>
            <a href="${pageContext.request.contextPath}/pages/cadastroTutor.jsp" class="btn btn-outline-light">Entre como Tutor</a>
            <a href="${pageContext.request.contextPath}/pages/login.jsp" class="btn btn-outline-light">Entrar</a>
            
        </div>
    </div>
</nav>

<section class="hero text-white">
    <div class="container d-flex justify-content-between align-items-center flex-wrap">

        
        <div class="hero-text col-md-4">
            <h1>Capacite-se para transformar realidades</h1>
            <p>Inclusão começa com conhecimento</p>
            <p>A nossa política é oferecer capacitação para preparar profissionais para <br>
             a diversidade e a inclusão no ambiente de trabalho e na sociedade.</p>
              </div>
        <div class="col-md-4 d-flex justify-content-center align-items-center">
            <img src="${pageContext.request.contextPath}/static/images/menina.png" alt="Inclusão digital" class="hero-img img-fluid">
        </div>
    </div>
 </section>
 <section class="how-it-works">
    <div class="container">
        <h2>Como funciona a plataforma?</h2>
        <div class="row justify-content-center">
            <div class="col-md-3 how-step">
                <div class="how-icon">🎓</div>
                <div class="how-title">Cadastre-se</div>
                <div class="how-desc">Crie uma conta como estudante ou tutor para começar a aprender ou ensinar.</div>
            </div>
            <div class="col-md-3 how-step">
                <div class="how-icon">📚</div>
                <div class="how-title">Escolha um curso</div>
                <div class="how-desc">Navegue pelos cursos disponíveis e escolha aquele que mais se adequa aos seus interesses.</div>
            </div>
            <div class="col-md-3 how-step">
                <div class="how-icon">🎥</div>
                <div class="how-title">Assista às videoaulas</div>
                <div class="how-desc">Aprenda no seu ritmo com aulas em vídeo preparadas por especialistas.</div>
            </div>
            <div class="col-md-3 how-step">
                <div class="how-icon">💬</div>
                <div class="how-title">Comente e interaja</div>
                <div class="how-desc">Participe com perguntas, comentários e sugestões para enriquecer sua aprendizagem.</div>
            </div>
        </div>
    </div>
</section>
 
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>