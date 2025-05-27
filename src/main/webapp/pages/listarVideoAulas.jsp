<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Vídeo Aulas</title>
</head>
<body>
<%
    Tutor tutor = (Tutor) session.getAttribute("usuarioLogado");
    if(tutor == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h1>Lista de Vídeo Aulas de <%= tutor.getRetornaNome() %></h1>

<%
    if(tutor.getVideoaulas() != null && !tutor.getVideoaulas().isEmpty()){
%>
    <ul>
<%
        for(VideoAula video : tutor.getVideoaulas()){
%>
        <li>
            <strong><%= video.getTitulo() %></strong><br>
            URL: <a href="<%=video.getUrl() %>" target="_blank"><%= video.getUrl() %></a><br>
            ID do Módulo: <%= video.getIdModulo() %>
        </li>
<%
        }
%>
    </ul>
<%
    }
    else{
%>
        <p>Vocẽ ainda não postou nenhuma vídeo aula.</p>
<%
    }
%>

<a href="videoaula.jsp">Voltar para o Painel de Vídeo Aulas</a>


</body>
</html>