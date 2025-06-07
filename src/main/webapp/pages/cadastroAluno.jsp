<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro - Aluno</title>
    <style>
        body {
            background: linear-gradient(to right, #1e3c72, #2a5298);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: white;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
        }

        .login-container {
            background-color: #2a5298;
            padding: 2.5rem;
            border-radius: 12px;
            width: 500px; 
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); 
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: white;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px; 
        }

        .form-row {
            display: flex;
            justify-content: space-between;
            gap: 20px; 
            margin-bottom: 15px;
        }

        .form-row .form-group {
            flex: 1;
            margin-bottom: 0; 
        }

        label {
            margin-bottom: 8px; 
            font-weight: 600;
        }

        input,
        textarea {
            padding: 12px;
            border: none;
            border-radius: 8px; 
            background-color: rgba(255, 255, 255, 0.95); 
            color: #333;
            font-size: 1rem;
            width: 100%; 
            box-sizing: border-box; 
        }

        input::placeholder,
        textarea::placeholder {
            color: #777;
        }

        textarea {
            resize: vertical; 
            min-height: 80px; 
        }

        button {
            margin-top: 25px;
            padding: 15px 20px; 
            background-color: #f0f0f0; 
            color: #2a5298;
            border: none;
            border-radius: 8px; 
            font-weight: bold;
            cursor: pointer;
            font-size: 1.1rem;
            transition: background-color 0.3s ease, transform 0.2s ease; 
        }

        button:hover {
            background-color: #e0e0e0; 
            transform: translateY(-2px); 
        }

        button:active {
            transform: translateY(0); 
        }
    </style>
</head>
<body>

<div class="login-container">
    <form action="${pageContext.request.contextPath}/cadastroAluno" method="post">
        <h2>Cadastro</h2>

        <div class="form-row">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" placeholder="Digite seu nome" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Digite seu email" required>
            </div>
        </div>

        <div class="form-group">
            <label for="bio">Biografia</label>
            <textarea id="bio name="bio" placeholder="Fale sobre você" rows="3" required></textarea>
        </div>

        <div class="form-group">
            <label for="senha">Digite sua senha</label>
            <input type="password" id="senha" name="senha" placeholder="Digite sua senha" required>
        </div>

        <button type="submit">Cadastrar</button>
    </form>
</div>

</body>
</html>