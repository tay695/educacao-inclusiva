<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno"%>

<%
Aluno aluno = (Aluno) request.getAttribute("aluno");
%>

<html lang="pt-br">
<head>
<title>Perfil do Aluno</title>
</head>
<body>
<a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath} assets/components/header.jsp">

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