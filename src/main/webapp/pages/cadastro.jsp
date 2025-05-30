<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Cadastro</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            min-height: 100vh;
            position: relative;
            overflow: hidden;
            background: url('${pageContext.request.contextPath}/static/images/fundo.png') no-repeat center center fixed;
            
        }

        .background-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url('images/fundo.jpg') center center / cover no-repeat;
            filter: blur(5px);
            opacity: 0.6;
            z-index: 0;
        }

        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0; width: 100%; height: 100%;
            background-color: rgba(0, 0, 0, 0.3);
            z-index: 0;
        }

        .form-container {
            position: relative;
            z-index: 1;
            max-width: 500px;
        }
    </style>
</head>

<body class="d-flex align-items-center justify-content-center">

    <div class="background-overlay"></div>

    <div class="container form-container">
        <form method="post" id="cadastroForm"
              class="p-4 rounded-4 shadow-lg text-white"
              style="background-color: #3465a4;">
            <h2 class="text-center mb-4">Cadastro</h2>

           
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
                    <input type="radio" class="form-check-input" name="tipoUsuario" value="aluno" checked onchange="atualizarFormulario()">
                    <label class="form-check-label">Aluno</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" name="tipoUsuario" value="tutor" onchange="atualizarFormulario()">
                    <label class="form-check-label">Tutor</label>
                </div>
            </div>

            <div class="mb-3" id="campoEspecializacao" style="display:none;">
                <label for="areaEspecializacao" class="form-label">Área de Especialização:</label>
                <input type="text" class="form-control" name="areaEspecializacao">
            </div>

            <div class="mb-3">
                <label for="bio" class="form-label">Biografia:</label>
                <textarea name="bio" class="form-control" rows="4"></textarea>
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha:</label>
                <input type="password" class="form-control" name="senha" required minlength="8">
            </div>

            <button type="submit" class="btn btn-light btn-lg px-5 mt-3 w-100">Cadastrar</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

    <script>
        function atualizarFormulario() {
            const tipo = document.querySelector("input[name='tipoUsuario']:checked").value;
            const form = document.getElementById("cadastroForm");

            // Define o action de acordo com o tipo
            if (tipo === "tutor") {
                form.action = "${pageContext.request.contextPath}/CadastroTutor";
                document.getElementById("campoEspecializacao").style.display = "block";
            } else {
                form.action = "${pageContext.request.contextPath}/CadastroAluno";
                document.getElementById("campoEspecializacao").style.display = "none";
            }
        }

        window.addEventListener("DOMContentLoaded", atualizarFormulario);
    </script>

</body>
</html>
