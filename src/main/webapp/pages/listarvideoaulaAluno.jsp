<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Vídeo Aulas</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/listarvideoaulaAluno.css">
</head>
<body>
<div class="main-content-wrapper">
		<h1 class="page-title">Vídeo Aulas Disponíveis</h1>
		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listarVideoAulas"> 
			</div>
		<c:choose>
			<c:when test="${empty  aulasDisponiveis}">
				<p class="subtext"> Não há vídeo aulas para exibir.</p>
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
							<c:forEach items="${ aulasDisponiveis}" var="va">
								<tr>
									<td><strong>${va.titulo}</strong></td>
									<td><a href="${va.url}" target="_blank"> <i
											class="bi bi-youtube me-1"></i>${va.url}
									</a></td>
									<td><c:forEach items="${modulos}" var="modulo">
											<c:if test="${modulo.id == va.moduloId}">
                                                ${modulo.titulo}
                                            </c:if>
										</c:forEach></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="nav-buttons">
			<a href="${pageContext.request.contextPath}/pages/dashboardAluno.jsp"
				class="btn btn-primary"> <i class="bi bi-arrow-left-circle me-1"></i>
				Voltar ao Painel
			</a>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>