<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ include file="/assets/components/header.jsp" %>
<%
Tutor tutor = (Tutor) request.getAttribute("tutor");
%>
<html lang="pt-br">
<head>
    <title>Perfil do Tutor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        .field-wrapper { display: flex; align-items: center; gap: 1rem; }
        .field-wrapper input, .field-wrapper textarea { flex: 1; }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="card shadow p-4">
        <h2 class="mb-4">Bem-vindo, <%= tutor.getRetornaNome() %> <small class="text-muted">(Tutor)</small></h2>

        <!-- Nome -->
        <div class="mb-3">
            <h5>Nome</h5>
            <div class="field-wrapper">
                <span id="nomeTexto"><%= tutor.getRetornaNome() %></span>
                <input type="text" id="nomeInput" class="form-control d-none" value="<%= tutor.getRetornaNome() %>">
                <button class="btn btn-sm btn-outline-primary" onclick="toggleEdit('nome')"><i class="bi bi-pencil"></i></button>
                <button class="btn btn-sm btn-success d-none" id="nomeSalvar" onclick="saveEdit('nome')"><i class="bi bi-check-lg"></i></button>
            </div>
        </div>

        <!-- Email -->
        <div class="mb-3">
            <h5>Email</h5>
            <div class="field-wrapper">
                <span id="emailTexto"><%= tutor.getEmail() %></span>
                <input type="email" id="emailInput" class="form-control d-none" value="<%= tutor.getEmail() %>">
                <button class="btn btn-sm btn-outline-primary" onclick="toggleEdit('email')"><i class="bi bi-pencil"></i></button>
                <button class="btn btn-sm btn-success d-none" id="emailSalvar" onclick="saveEdit('email')"><i class="bi bi-check-lg"></i></button>
            </div>
        </div>

        <!-- Bio -->
        <div class="mb-3">
            <h5>Bio</h5>
            <div class="field-wrapper">
                <span id="bioTexto"><%= tutor.getBio() %></span>
                <textarea id="bioInput" class="form-control d-none" rows="3"><%= tutor.getBio() %></textarea>
                <button class="btn btn-sm btn-outline-primary" onclick="toggleEdit('bio')"><i class="bi bi-pencil"></i></button>
                <button class="btn btn-sm btn-success d-none" id="bioSalvar" onclick="saveEdit('bio')"><i class="bi bi-check-lg"></i></button>
            </div>
        </div>

        <!-- Área de Especialização -->
        <div class="mb-3">
            <h5>Área de Especialização</h5>
            <div class="field-wrapper">
                <span id="areaTexto"><%= tutor.getAreaEspecializacao() %></span>
                <input type="text" id="areaInput" class="form-control d-none" value="<%= tutor.getAreaEspecializacao() %>">
                <button class="btn btn-sm btn-outline-primary" onclick="toggleEdit('area')"><i class="bi bi-pencil"></i></button>
                <button class="btn btn-sm btn-success d-none" id="areaSalvar" onclick="saveEdit('area')"><i class="bi bi-check-lg"></i></button>
            </div>
        </div>
    </div>
</div>

<script>
    const tutorId = <%= tutor.getId() %>;

    function toggleEdit(field) {
        document.getElementById(`${field}Texto`).classList.add('d-none');
        document.getElementById(`${field}Input`).classList.remove('d-none');
        document.getElementById(`${field}Salvar`).classList.remove('d-none');
    }

    function saveEdit(field) {
        const valor = document.getElementById(`${field}Input`).value;
        fetch('AtualizarPerfilTutorServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `campo=${field}&valor=${encodeURIComponent(valor)}&id=${tutorId}`
        }).then(resp => {
            if (resp.ok) {
                document.getElementById(`${field}Texto`).textContent = valor;
                document.getElementById(`${field}Texto`).classList.remove('d-none');
                document.getElementById(`${field}Input`).classList.add('d-none');
                document.getElementById(`${field}Salvar`).classList.add('d-none');
            } else {
                alert("Erro ao atualizar.");
            }
        });
    }
</script>
</body>
</html>