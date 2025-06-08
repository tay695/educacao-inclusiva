<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background: linear-gradient(to right, #1e3c72, #2a5298);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	color: white;
	font-family: 'Segoe UI';
}

.login-container {
	background-color: #2a5298;
	padding: 2.5rem;
	border-radius: 12px;
	width: 400px;
}

.login-form {
	display: flex;
	flex-direction: column;
}

.login-form label {
	margin-top: 15px;
}

.login-form input, .login-form textarea {
	padding: 12px;
	border: none;
	border-radius: 5px;
	margin-top: 5px;
	background-color: white;
	color: black;
}

.login-form button {
	margin-top: 20px;
	padding: 20px;
	background-color: white;
	color: #2a5298;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
}
</style>
</head>

</head>
<body>

	<div class="login-container">
		<form action="${pageContext.request.contextPath}/cadastroTutor" method="post" class="login-form">
			<h2>Cadastro</h2>
			<label for="areaEspecializacao"> Qual sua área de especialização </label>
			 <input type="text" id="areaEspecializacao" name="area_especializacao" placeholder="informe sua especialização"required> 
	          <label for="nome"> Nome </label> <input type="text" id="nome" name="nome" placeholder="Digite seu nome" required>

			<label for="email">Email</label> <input type="email" id="email"name="email" placeholder="Digite seu email" required> <label
				for="bio">Biografia</label>	<textarea id="bio" name="bio" placeholder="Fale sobre você" rows="3"
				required></textarea>

			<label for="senha">Digite sua senha</label> <input type="password"
				id="senha" name="senha" placeholder="Digite sua senha" required>

			<button type="submit">Cadastrar</button>
		</form>
	</div>

</body>
</html>
