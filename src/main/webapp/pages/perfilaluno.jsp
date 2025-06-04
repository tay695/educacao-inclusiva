<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>

<%
Aluno aluno = (Aluno) request.getAttribute("aluno");
%>

<html lang="pt-br">
<head>
<title>Perfil do Aluno</title>
<style type="text/css">
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
	<h2>
		Bem-vindo,
		<%=aluno.getRetornaNome()%>
		(Aluno)
	</h2>
	<p>
		Email:
		<%=aluno.getEmail()%></p>
	<p>
		Bio:
		<%=aluno.getBio()%></p>
</body>