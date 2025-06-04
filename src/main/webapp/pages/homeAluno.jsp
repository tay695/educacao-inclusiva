<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cursos Disponíveis</title>
      
    <style type="text/css">
  
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

h2 {
    margin-bottom: 20px;
}

.cursos-container {
    display: flex;
    flex-wrap: wrap;
}

.card {
    padding: 20px;
    margin: 15px;
    border-radius: 12px;
    color: white;
    width: 250px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.azul {
    background-color: #007BFF;
}

.ciano {
    background-color: #17A2B8;
}

.rosa {
    background-color: #E83E8C;
}

.marrom {
    background-color: #795548;
}

.vinho {
    background-color: #6f0427;
}

.azul-escuro {
    background-color: #343a40;
}

.inscrever-btn {
    margin-top: 10px;
    padding: 8px 15px;
    background-color: white;
    color: black;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.inscrever-btn:hover {
    background-color: #f0f0f0;
}
    </style>
</head>
<body>

  <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="${pageContext.request.contextPath}/pages/homeTutor.jsp" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" height="65" />
            </a>
        </div>
    </nav>
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu Aluno</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/perfilaluno.jsp"><i class="bi bi-person-circle me-2"></i>Meu Perfil</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/videoaulaAluno.jsp"><i class="bi bi-camera-video me-2"></i>Minhas aulas</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/listarvideoaulaAluno.jsp"><i class="bi bi-folder-plus me-2"></i>Cursos inscritos</a></li>
                <li><hr /></li>
                <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/controller/Logout"><i class="bi bi-box-arrow-right me-2"></i>Sair</a></li>
            </ul>
        </div>
    </div>
<h2>Cursos Disponíveis</h2>

<%
    String[] cores = {"azul", "ciano", "rosa", "marrom", "vinho", "azul-escuro"};
    java.util.Random rand = new java.util.Random();
%>

<div class="cursos-container">
    <c:forEach var="curso" items="${cursos}">
        <%
            String corAleatoria = cores[rand.nextInt(cores.length)];
        %>
        <div class="card <%= corAleatoria %>">
            <h3><c:out value="${curso.titulo}" /></h3>
            <p><c:out value="${curso.descricao}" /></p>
            <form method="post" action="inscricao">
                <input type="hidden" name="idCurso" value="${curso.id}" />
                <button type="submit" class="inscrever-btn">Inscrever-se</button>
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>