<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar nova vídeo aula</title>
</head>
<body>
<a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath} assets/components/header.jsp">


    <h1>Cadastrar nova vídeo aula</h1>

    <form action="videoaula" method="post">
        <label for="titulo">Título:</label>
        <input type="text" name="titulo" id="titulo" required><br>

        <label for="url"> Link da vídeo aula: </label><br>
        <input type="number" name="IdModulo" id="IdModulo" required>

        <input type="submit" value="Cadastrar">
    </form>
    <br><a href="videoaula.jsp">Voltar ao Painel de Video Aula</a>
</body>
</html>