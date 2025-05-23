<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Educação Inclusiva</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>

<!-- Barra de navegação Bootstrap -->
<nav class="navbar navbar-dark bg-dark navbar-expand-lg fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand me-5" href="#">Educação Inclusiva</a>

        <form action="${pageContext.request.contextPath}/search" method="post" class="d-flex">
            <input type="text" class="form-control me-2" name="pesquisa" placeholder="Buscar" required />
            <button type="submit" class="btn btn-success">Buscar</button>
        </form>

        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
            </li>
        </ul>
    </div>
</nav>
