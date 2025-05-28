<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar nova vídeo aula</title>
</head>
<body>

    <h1>Cadastrar nova vídeo aula</h1>

    <form action="videoaula" method="post">
        <div>
            <label for="titulo">Título:</label>
            <input type="text" name="titulo" id="titulo" required><br>
        </div>
        <br>
        <div> 
            <label for="url"> Link da vídeo aula: </label><br>
            <input type="url" name="url" id="url" placeholder="htttps://..." required>
        </div>
        <br>
        <div> 
            <label for="idModulo">ID do Módulo:</label><br>
            <input type="number" name="idModulo" id="idModulo" min="1" required>
        </div>
        <br>
        <div> 
        <input type="submit" value="Cadastrar">
        </div>
    </form>

    <br><a href="videoaulaTutor.jsp">Voltar ao Painel de Video Aula</a>
</body>
</html>