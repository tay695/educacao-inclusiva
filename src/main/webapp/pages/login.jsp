<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title> Login </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<body class="d-flex justify-content-center align-items-center" style="height: 100vh;">
<form action="${pageContext.request.contextPath}/login" method="post" class="p-4 border rounded" style="min-width: 450px;">
    <input type="text" name="email" placeholder="Email" required class="form-control mb-3" style="height: 45px; font-size: 1.2rem;" />
    <input type="password" name="senha" placeholder="Senha" required class="form-control mb-3" style="height: 45px; font-size: 1.2rem;" />
    <button type="submit" class="btn btn-primary w-100 btn-lg">Entrar</button>

        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {
        %>
            <p class="text-danger mt-3"><%= erro %></p>
        <% } %>
        
</form>
</body>
</html>
