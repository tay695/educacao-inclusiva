<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro Módulo e Vídeo Aula</title>
</head>
<body>

<h2>Cadastro de Módulo e Vídeo Aula</h2>

<form action="${pageContext.request.contextPath}/modulovideo" method="post">
    <input type="hidden" name="action" value="inserir" />

    <h3>Dados do Módulo</h3>
    <label for="titulo">Título do Módulo:</label><br/>
    <input type="text" id="titulo" name="titulo" required /><br/>

    <label for="descricaoModulo">Descrição do Módulo:</label><br/>
    <textarea id="descricao" name="descricao" required></textarea><br/>

    <h3>Dados da Vídeo Aula (opcional)</h3>
    <label for="titulo">Título do Vídeo:</label><br/>
    <input type="text" id="tituloV" name="titulo" /><br/>

    <label for="url">URL do Vídeo:</label><br/>
    <input type="url" id="url" name="url" placeholder="https://..." /><br/>

    <button type="submit">Cadastrar</button>
</form>

<%
    String sucesso = (String) session.getAttribute("sucesso");
    String erro = (String) session.getAttribute("erro");
    if (sucesso != null) {
%>
        <p style="color:green;"><%= sucesso %></p>
<%
        session.removeAttribute("sucesso");
    }
    if (erro != null) {
%>
        <p style="color:red;"><%= erro %></p>
<%
        session.removeAttribute("erro");
    }
%>

</body>
</html>
