<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Modulo" %>
<%@ page import="java.util.List" %>

<%
    VideoAula video = (VideoAula) request.getAttribute("video");
    List<Modulo> modulos = (List<Modulo>) request.getAttribute("modulos");
    int idModuloSelecionado = (video != null) ? video.getIdModulo() : -1;
%>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= video==null?"Cadastrar":"Editar" %> Video Aula</title>
</head>
<body>

    <h1><%= video == null ? "Cadastrar" : "Editar" %> Vídeo aula</h1>

    <form action="<%= request.getContextPath() %>/videoaula" method="post">
        
        <% if (video != null){ %>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= video.getId() %>">
        <% } else { %>
            <input type="hidden" name="action" value="create">
        <% } %>
        
            <label>Título:</label><br>
            <input type="text" name="titulo" id="titulo" required value="<%= video!=null?video.getTitulo():"" %>"><br><br>
        
            
            <label>Link da vídeo aula: </label><br>
            <input type="url" name="url" id="url" placeholder="htttps://..." required value="<%= video!=null?video.getUrl():"" %>"><br><br>
        

            <label>Módulo:</label><br>

            <select name="idModulo" required>
        <%
            if(modulos != null && !modulos.isEmpty()){
                for(Modulo modulo : modulos){
                    boolean selecionado = (modulo.getId() == idModuloSelecionado);
        %>
            <option value="<%= modulo.getId() %>" <%= selecionado ? "selected" : "" %>>
                <%= modulo.getTitulo() %>
            </option>

        <%
            }

            }
            else{
        %>

            <option value="">Nenhum módulo disponível</option>
        <% 

                }
        %>
        </select><br><br>

        <button type="submit"><%= video==null?"Cadastrar":"Salvar" %></button>
    </form>

    <p><a href="listarvideoaulaTutor.jsp">Voltar a listagem de vídeo aulas</a></p>
    <br><a href="videoaulaTutor.jsp">Voltar ao Painel de Video Aula</a>
</body>
</html>