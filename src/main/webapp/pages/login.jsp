<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title> Login </title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="email" placeholder="Email" required />
        <input type="password" name="senha" placeholder="Senha" required />
        <button type="submit"> Entrar </button>

        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {
        %>
            <p style="color: red;"><%= erro %></p>
        <% } %>
    </form>
</body>
</html>
