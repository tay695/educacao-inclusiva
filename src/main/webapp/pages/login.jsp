<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<body>

    <%
        String erro = request.getParameter("erro");
        if ("1".equals(erro)) {
    %>
        <p style="color: red;">Email ou senha inválidos.</p>
    <%
        }
    %>
	<div class="login-container">
		<div class="login-card">
			<form action="${pageContext.request.contextPath}/login" method="post"
				class="login-form">
				<h2>LOGIN</h2>
				<label for="email">Email</label> <input type="email" id="email"
					name="email" placeholder="Digite seu email" required> <label
					for="senha">Senha</label> <input type="password" id="senha"
					name="senha" placeholder="Digite sua senha" required>

				<button type="submit">Entrar</button>
			</form>
		</div>
	</div>
</body>
</html>
