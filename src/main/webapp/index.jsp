<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Capacita++</title>    
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
</head>
<body class="m-0 p-0">
<nav class="navbar">
    <div class="container-nav">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo Capacita++" height="50">
        </a>
        <div class="nav-buttons">
            <a href="${pageContext.request.contextPath}/pages/cadastroAluno.jsp">Estudante</a>
            <a href="${pageContext.request.contextPath}/pages/cadastroTutor.jsp">Tutor</a>
            <a href="${pageContext.request.contextPath}/pages/login.jsp">Entrar</a>
        </div>
    </div>
</nav>

<section class="hero">
    <div class="hero-content">
        <h1>Capacite-se para transformar realidades</h1>
        <p>Inclusão começa com conhecimento.</p>
        <p>Oferecemos capacitação para preparar profissionais para a diversidade e a inclusão no trabalho e na sociedade.</p>
    </div>
</section>

<section class="how-it-works">
    <h2>Como funciona a plataforma?</h2>
    <div class="steps">
        <div class="step">
            <h3>📝 Cadastre-se</h3>
            <p>Crie uma conta como estudante ou tutor para começar.</p>
        </div>
        <div class="step">
            <h3>📚 Escolha um curso</h3>
            <p>Explore os cursos disponíveis e selecione o ideal.</p>
        </div>
        <div class="step">
            <h3>🎥 Assista às videoaulas</h3>
            <p>Aprenda com especialistas no seu ritmo.</p>
        </div>
        <div class="step">
            <h3>💬 Comente e interaja</h3>
            <p>Participe com perguntas e sugestões para enriquecer sua jornada.</p>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
<footer>
    <p>&copy; 2025 Capacita++. Todos os direitos reservados.</p>
</footer>

</body>
</html>