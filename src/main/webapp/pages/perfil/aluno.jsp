<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%
    Aluno aluno = (Aluno) request.getAttribute("aluno");
%>
<html lang="pt-br">
<head><title>Perfil do Aluno</title></head>
<body>
<h2>Bem-vindo, <%= aluno.getRetornaNome() %> (Aluno)</h2>
<p>Email: <%= aluno.getEmail() %></p>
<p>Bio: <%= aluno.getBio() %></p>
<p>Data Nascimento: <%= aluno.getDataNascimento() %></p>
</body>
</html>