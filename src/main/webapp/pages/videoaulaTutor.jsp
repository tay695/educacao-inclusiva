<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Tutor tutor = (Tutor) session.getAttribute("usuarioLogado");
    if (tutor == null) {
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel de Vídeo Aulas</title>
</head>
<body>

<h1>Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
<p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

<h2>Menu de Gerenciamento de Vídeo Aulas</h2>
<ul>
    <li><a href="formularioCadastroVideoAula.jsp">Postar nova video aula</a></li>
    <li><a href="listarvideoaulaTutor.jsp">Visualizar todas as video aulas</a></li>
    <li><a href="logout.jsp">Sair</a></li>
</ul>

</body>
</html>
