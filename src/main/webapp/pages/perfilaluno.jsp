<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno"%>

<%
Aluno aluno = (Aluno) request.getAttribute("aluno");
%>
<html lang="pt-br">
<head>
<title>Perfil do Aluno</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
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