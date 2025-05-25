<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Painel de Vídeo Aulas</title>
</head>
<body>

<%
HttpSession sessao = request.getSession(false);
Tutor tutor = null;
if (sessao != null) {
    Object userObj = sessao.getAttribute("usuarioLogado");
    if (userObj != null && userObj instanceof Tutor) {
        tutor = (Tutor) userObj;
    }
}
%>

<% if (tutor != null) { %>


	<h1>Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
	<p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

	<h2>Menu de Gerenciamento de Vídeo Aulas</h2>
	<ul>
		<li><a href="formCadastrarVideoAula.jsp">Postar nova video aula</a></li>
		<li><a href="listarVideoAula.jsp">Visualizar todas as video aulas</a></li>
		<li><a href="logout.jsp">Sair</a></li>
	</ul>

	<h2>Suas Video Aulas</h2>
	<%
        if(tutor.getVideoaulas() != null && !tutor.getVideoaulas().isEmpty()){

    %>
	<ul>
		<% 
            for(VideoAula video : tutor.getVideoaulas()){
        %>

		<li>
            <strong><%= video.getTitulo() %></strong><br>
            URL : <a href="<%= video.getUrl() %>" target="_blank"><%= video.getUrl() %></a><br> 
            ID do Modulo: <%= video.getIdModulo() %>
        </li>

		<%
            }
        %>
	</ul>
	<%
        }
        else{
    %>
	<p>Você ainda não postou nenhuma vídeo aula.</p>
	<%
        }
    %>
     }
     else{
    %>
        <p>Usuário não autenticadi ou sessão expirada.</p>
    %>
     }
    %>

</body>
</html>