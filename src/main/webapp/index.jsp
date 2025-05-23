<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>CAPACITA++</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5 pt-5 text-center">
    <h1>Bem-vindo ao CAPACITA++</h1>
    <p class="lead">Plataforma de formação e inclusão educacional.</p>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary me-2">Login</a>
        <a href="${pageContext.request.contextPath}/cadastro" class="btn btn-success">Cadastrar-se</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>

