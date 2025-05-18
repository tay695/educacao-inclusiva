<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sessao = request.getSession(false);
    Aluno aluno = (Aluno) sessao.getAttribute("usuarioLogado");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Portal estudante </title>
</head>
<body>
    <h1>Olá, <%= aluno.getRetornaNome() %>!</h1>
    

    <h2>O que você deseja fazer?</h2>
    <ul>
        <li><a href="#">Explorar cursos disponíveis</a></li>
        <li><a href="#">Meus cursos</a></li>
        <li><a href="#">Enviar mensagem para tutor</a></li>
        <Li><a href="#">Deixa sua opinião com um comentário</a>
        <li><a href="logout.jsp">Sair</a></li>
    </ul>
</body>
</html>
