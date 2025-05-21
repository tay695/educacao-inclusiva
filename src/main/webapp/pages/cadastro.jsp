<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="estilos.css">
    
</head>
<body>
    <form action="CadastroUsuario" method="post" id="cadastroForm">
    

        <label for="nome">Nome:</label><br>
        <input type="text" name="nome" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" name="email" required><br><br>
        
            <label>Tipo de Usuário:</label><br>
        <input type="radio" name="tipo Usuario" value="aluno" checked onclick="mostrarCampoEspecializacao(false)"> Aluno
        <input type="radio" name="tipo Usuario" value="tutor" onclick="mostrarCampoEspecializacao(true)"> Tutor
        <br><br>
        
         <div id="campoEspecializacao" style="display:none;">
            <label for="areaEspecializacao">Área de Especialização:</label><br>
            <input type="text" name="areaEspecializacao"><br><br>
        </div>
        
      <label for="dataNascimento">Data de Nascimento:</label><br>
        <input type="date" name="dataNascimento" required><br><br>
        
        <label for="bio">Biografia:</label><br>
        <textarea name="bio" rows="4" cols="40"></textarea><br><br>
        
        
        <label for="senha">Senha:</label><br>
        <input type="password" name="senha" required minlength="8"><br><br>

        
        <input type="submit" value="Cadastrar">
    </form>

    <script>
        function mostrarCampoEspecializacao(mostrar) {
            const campo = document.getElementById("campoEspecializacao");
            campo.style.display = mostrar ? "block" : "none";
        }

        window.addEventListener("DOMContentLoaded", function () {
            const tipo = document.querySelector("input[name='tipoUsuario']:checked").value;
            mostrarCampoEspecializacao(tipo === "tutor");
        });
    </script>
</body>
</html>
