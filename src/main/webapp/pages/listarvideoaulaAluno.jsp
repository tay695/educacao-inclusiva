<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
HttpSession sessao = request.getSession();

//BLOCO DE TESTE(REMOVER)

if(request.getAttribute("videoAulas") == null){
    List<VideoAula> teste = new ArrayList<>();
        teste.add(new VideoAula(1, "Aula Exemplo 1", "https://youtu.be/exemplo1", 101));
        teste.add(new VideoAula(2, "Aula Exemplo 2", "https://youtu.be/exemplo2", 101));
        request.setAttribute("videoAulas", teste);
    }
    //FIM DO BLOCO TESTE

    @SuppressWarnings("unchecked")
    List<VideoAula> videoAulas = (List<VideoAula>) request.getAttribute("videoAulas");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vídeo Aulas</title>
</head>
<body>

    <h1>Vídeo Aulas Disponíveis</h1>

    <% if (videoAulas == null || videoAulas.isEmpty()){ %>

        <p>Não há vídeo aulas para exibir.</p>
    <% } else{ %>
        <ul>

        <% for(VideoAula va : videoAulas) { %>

        <li>
            <strong><%= va.getTitulo() %></strong><br>
            <a href="<%= va.getUrl() %>" target="_blank"><%= va.getUrl() %></a>
            <p>ID do Módulo> <%= va.getIdModulo() %></p>
        </li>

        <% } %> 

        </ul> 

    <% } %>

        <p><a href="videoaulaAluno.jsp">Voltar ao painel de video aula</a></p>
        <p><a href="perfilaluno.jsp">Voltar ao painel de aluno</a></p>

</body>
</html>