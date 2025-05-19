<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%
    Tutor tutor = (Tutor) request.getAttribute("tutor");
%>
<html lang="pt-br">
<head><title>Perfil do Tutor</title></head>
<body>
<h2>Bem-vindo, <%= tutor.getRetornaNome() %> (Tutor)</h2>
<p>Email: <%= tutor.getEmail() %></p>
<p>Bio: <%= tutor.getBio() %></p>
<p>Área de Especialização: <%= tutor.getAreaEspecializacao() %></p>
</body>
</html>