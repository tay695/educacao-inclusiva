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
            teste.add(new VideoAula(2, "Aula Exemplo 2", "https://youtu.be/exemplo2", 102));
            teste.add(new VideoAula(3, "Aula Exemplo 3", "https://youtu.be/exemplo3", 103));
            request.setAttribute("videoAulas", teste);
    }
    //FIM DO BLOCO DE TESTE

    @SuppressWarnings("unchecked")
    List<VideoAula> videoAulas = (List<VideoAula>) request.getAttribute("videoAulas");
%>

<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Vídeo Aulas (Tutor)</title>
</head>
<body>

    <h1>Gerenciamento de Vídeo Aulas</h1>
    <p><a href="formularioCadastroVideoAula.jsp">+Cadastrar nova video aula</a></p>

    <% if (videoAulas == null || videoAulas.isEmpty()){ %>
        <p>Você ainda não cadastrou nenhuma vídeo aula.</p>
    <% } else { %>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>ID</th><th>Título</th><th>URL</th><th>Módulo</th><th>Ações</th>
            </tr>
            <% for(VideoAula va : videoAulas) { %>
                <tr>
                    <td><%= va.getId() %></td>
                    <td><%= va.getTitulo() %></td>
                    <td><a href="<%= va.getUrl() %>" target="_blank">ver</a></td>
                    <td><%= va.getIdModulo() %></td>
                    <td>
                        <a href="edutarVideoAula.jsp?id=<%= va.getId() %>">Editar</a> |
                        <a href="excluirVideoAula?id=<%= va.getId() %>">Excluir</a>
                    </td>
                </tr>
           <% } %>
        </table>
   <% } %>

   <p><a href="videoaulaTutor.jsp">Voltar ao painel de Vídeo Aula</a></p>
   <p><a href="videoaulaTutor.jsp">Voltar ao painel de Tutor</a></p>

</body>
</html>