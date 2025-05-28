<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- Estilo personalizado -->
</head>
<body>

<div class="login-card">
    <h2><i class="bi bi-person-circle me-2"></i>Login</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <input type="text" name="email" class="form-control" placeholder="Email" required />
        </div>
        <div class="mb-4">
            <input type="password" name="senha" class="form-control" placeholder="Senha" required />
        </div>
        <button type="submit" class="btn btn-primary w-100">Entrar</button>
    </form>
</div>

</body>
</html>
