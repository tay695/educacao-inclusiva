<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cadastroAluno.css">
    <title>Cadastro - Aluno</title>
<title>Cadastro -Aluno </title>
</head>
<body class="d-flex align-items-center justify-content-center">
    <div class="background-overlay"></div>
    <div class="container form-container">
        <form method="post" action="${pageContext.request.contextPath}/CadastroAluno"
              class="p-4 rounded-4 shadow-lg text-white"
              style="background-color: #3465a4;">
            <h2 class="text-center mb-4">Cadastro</h2>

            <div class="mb-3">
                <label class="form-label">Nome:</label>
                <input type="text" class="form-control" name="nome" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Email:</label>
                <input type="email" class="form-control" name="email" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Biografia:</label>
                <textarea name="bio" class="form-control" rows="4"></textarea>
            </div>

            <div class="mb-3">
                <label class="form-label">Senha:</label>
                <input type="password" class="form-control" name="senha" required minlength="8">
            </div>

            <button type="submit" class="btn btn-light btn-lg px-5 mt-3 w-100">Cadastrar</button>
        </form>
    </div>
</body>
</html>
