<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>

<%
Aluno aluno = (Aluno) request.getAttribute("aluno");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Perfil do Aluno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/perfilAluno.css" rel="stylesheet"> 
</head>
<body>

<div class="container py-5">
    <div class="profile-card mx-auto">
        <div class="text-center">
            <div class="icon-circle mx-auto mb-3">
                <i class="bi bi-person-fill display-4 text-white"></i>
            </div>
            <h3 class="fw-bold"><%= aluno.getRetornaNome() %></h3>
            <p class="text-muted"><%= aluno.getEmail() %></p>
            <button class="btn btn-outline-primary btn-sm">Editar perfil</button>
        </div>
        <hr>
        <div class="mt-4">
            <h6 class="text-primary fw-bold">Nome:</h6>
            <p><%= aluno.getRetornaNome() %> <button class="btn btn-sm btn-outline-secondary">Editar Nome</button></p>

            <h6 class="text-primary fw-bold">Email:</h6>
            <p><%= aluno.getEmail() %> <button class="btn btn-sm btn-outline-secondary">Editar Email</button></p>

            <h6 class="text-primary fw-bold">Bio:</h6>
            <p><%= aluno.getBio() != null ? aluno.getBio() : "Nenhuma bio cadastrada." %></p>
        </div>
    </div>
</div>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</body>
</html>