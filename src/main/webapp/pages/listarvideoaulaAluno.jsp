<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Vídeo Aulas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/listarvideoaulaAluno.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-light me-2"
                    type="button"
                    data-bs-toggle="offcanvas"
                    data-bs-target="#menuLateral"
                    aria-controls="menuLateral"
                    aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="${pageContext.request.contextPath}/homeAluno" class="navbar-brand text-white">
                <img src="${pageContext.request.contextPath}/static/images/logo.png"
                     alt="Logo da Educação Inclusiva"
                     height="65">
            </a>
        </div>
    </nav>

    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header bg-primary text-white">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Aluno</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar menu"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/perfilAluno">
                        <i class="bi bi-person-circle me-2"></i> Meu Perfil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listarVideoAulas">
                        <i class="bi bi-camera-video me-2"></i> Ver Vídeo Aulas
                    </a>
                </li>
                <li><hr></li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">
                        <i class="bi bi-box-arrow-right me-2"></i> Sair
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-content-wrapper">
        <h1 class="page-title">Vídeo Aulas Disponíveis</h1>

        <c:choose>
            <c:when test="${empty videoAulas}">
                <p class="subtext">Não há vídeo aulas para exibir.</p>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">Título</th>
                                <th scope="col">Link</th>
                                <th scope="col">Módulo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${videoAulas}" var="va">
                                <tr>
                                    <td><strong>${va.titulo}</strong></td>
                                    <td>
                                        <a href="${va.url}" target="_blank">
                                            <i class="bi bi-youtube me-1"></i>${va.url}
                                        </a>
                                    </td>
                                    <td>
                                        <c:forEach items="${modulos}" var="modulo">
                                            <c:if test="${modulo.id == va.moduloId}">
                                                ${modulo.titulo}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="nav-buttons">
            <a href="${pageContext.request.contextPath}/homeAluno" class="btn btn-primary">
                <i class="bi bi-arrow-left-circle me-1"></i> Voltar ao Painel
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>