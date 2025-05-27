<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel de Vídeo Aulas</title>
</head>
<body>

<%
   /* CODIGO ORIGINAL (descomentar quando o sistema estiver pronto)
    HttpSession sessao = request.getSession(false);
    Tutor tutor = null;
    if (sessao != null) {
        Object userObj = sessao.getAttribute("usuarioLogado");
        if (userObj != null && userObj instanceof Tutor) {
            tutor = (Tutor) userObj;
        }
    }

    if (tutor != null) { 
   */
%>


<%
   
    // BLOCO DE TESTE TEMPORÁRIO
    // Remover quando for usar autenticação real
    Tutor tutor = new Tutor("Área de Teste", 1, "Usuário Teste", "teste@exemplo.com", "123456", "Bio de Teste");

    // Criando uma videoaula falsa
    VideoAula videoTeste = new VideoAula(1, "Aula Teste", "http://exemplo.com/aulateste", 1);

    // Adicionando a videoaula ao tutor
    tutor.getVideoaulas().add(videoTeste);

    // Guardando na sessão para simular login
    session.setAttribute("usuarioLogado", tutor);
   
%>

<h1>Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
<p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

<h2>Menu de Gerenciamento de Vídeo Aulas</h2>
<ul>
    <li><a href="formularioCadastroVideoAula.jsp">Postar nova video aula</a></li>
    <li><a href="listarVideoAulas.jsp">Visualizar todas as video aulas</a></li>
    <li><a href="logout.jsp">Sair</a></li>
</ul>
<% if (tutor != null) { %>
    <h1>Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
    <p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

    <h2>Menu de Gerenciamento de Vídeo Aulas</h2>
    <ul>
        <li><a href="formCadastrarVideoAula.jsp">Postar nova video aula</a></li>
        <li><a href="listarVideoAula.jsp">Visualizar todas as video aulas</a></li>
        <li><a href="logout.jsp">Sair</a></li>
    </ul>

    <h2>Suas Videos Aulas</h2>
    <%
        if (tutor.getVideoaulas() != null && !tutor.getVideoaulas().isEmpty()) {
    %>
        <ul>
            <% for(VideoAula video : tutor.getVideoaulas()) { %>
                <li>
                    <strong><%= video.getTitulo() %></strong><br>
                    URL: <a href="<%= video.getUrl() %>" target="_blank"><%= video.getUrl() %></a><br>
                    ID do Modulo: <%= video.getIdModulo() %>
                </li>
            <% } %>
        </ul>
    <%
        } else {
    %>
        <p>Você ainda não postou nenhuma vídeo aula.</p>
    <%
        }
    %>
<% } else { %>
    <p>Usuário não autenticado ou sessão expirada.</p>
<% } %>


</body>
</html>
