<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- link com o css  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cadastro.css">
</head>
</head>
<body class="bg-light">

<div class="container mt-5 pt-5">
    <h2 class="mb-4">Cadastro de Usuário</h2>
    <form action="CadastroUsuario" method="post" id="cadastroForm" class="p-4 bg-white rounded shadow-sm">

        <div class="mb-3">
            <label for="nome" class="form-label">Nome:</label>
            <input type="text" class="form-control" name="nome" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" name="email" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Tipo de Usuário:</label><br>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="tipoUsuario" value="aluno" checked onclick="mostrarCampoEspecializacao(false)">
                <label class="form-check-label">Aluno</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="tipoUsuario" value="tutor" onclick="mostrarCampoEspecializacao(true)">
                <label class="form-check-label">Tutor</label>
            </div>
        </div>

        <div class="mb-3" id="campoEspecializacao" style="display:none;">
            <label for="areaEspecializacao" class="form-label">Área de Especialização:</label>
            <input type="text" class="form-control" name="areaEspecializacao">
        </div>

        <div class="mb-3">
            <label for="dataNascimento" class="form-label">Data de Nascimento:</label>
            <input type="date" class="form-control" name="dataNascimento" required>
        </div>

        <div class="mb-3">
            <label for="bio" class="form-label">Biografia:</label>
            <textarea name="bio" class="form-control" rows="4"></textarea>
        </div>

        <div class="mb-3">
            <label for="senha" class="form-label">Senha:</label>
            <input type="password" class="form-control" name="senha" required minlength="8">
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

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
