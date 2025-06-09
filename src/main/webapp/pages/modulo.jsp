<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty erro}">
    <div class="alert alert-danger">
        ${erro}
    </div>
</c:if>
<!DOCTYPE html>
<html lang="pt-br">
<head>
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h1 class="h2">Gerenciar Módulos</h1>
                    <a href="${pageContext.request.contextPath}/pages/dashboardTutor.jsp" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Voltar ao inicio
                    </a>
                </div>

                <c:if test="${not empty erro}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        ${erro}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                
                <c:if test="${not empty sucesso}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i>
                        ${sucesso}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <div class="card mb-4 shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h3 class="h5 mb-0">
                            <i class="bi bi-plus-circle me-2"></i>
                            ${param.idModulo != null ? 'Editar Módulo' : 'Criar Novo Módulo'}
                        </h3>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/modulo" method="post" id="moduleForm">
                            <input type="hidden" name="id" value="${modulo.id}">
                            <input type="hidden" name="redirect" value="videoaula">

                            <div class="mb-3">
                                <label for="titulo" class="form-label">Título do Módulo</label>
                                <input type="text" class="form-control" name="titulo" id="titulo" required 
                                    value="${param.titulo}" 
                                    placeholder="Ex: 'Introdução à Educação Inclusiva'">
                            </div>

                            <div class="mb-3">
                                <label for="descricao" class="form-label">Descrição</label>
                                <textarea class="form-control" name="descricao" id="descricao" rows="3" required
                                    placeholder="Descreva os objetivos e conteúdo deste módulo">${param.descricao}</textarea>
                            </div>

                            
                                
                                <button type="submit" name="action" value="${param.idModulo != null ? 'atualizar' : 'inserir'}" 
                                    class="btn btn-primary ms-auto">
                                    <i class="bi bi-save me-2"></i>
                                    ${param.idModulo != null ? 'Atualizar' : 'Salvar'} Módulo
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Lista de Módulos -->
                <div class="card shadow-sm">
                    <div class="card-header bg-white">
                        <h3 class="h5 mb-0">
                            <i class="bi bi-collection me-2"></i>
                            Módulos do Curso
                        </h3>
                    </div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${not empty modulos}">
                                <div class="row">
                                    <c:forEach items="${modulos}" var="modulo">
                                        <div class="col-md-6 mb-4">
                                            <div class="card module-card h-100">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between align-items-start">
                                                        <h4 class="card-title">${modulo.titulo}</h4>
                                                        <span class="badge bg-primary rounded-pill position-relative">
                                                            ${modulo.videoAulas != null ? modulo.videoAulas.size() : 0} vídeos
                                                        </span>
                                                    </div>
                                                    <p class="card-text text-muted">${modulo.descricao}</p>
                                                </div>
                                                <div class="card-footer bg-white border-top-0 d-flex justify-content-between">
                                                    <div class="action-buttons">
                                                        <!-- Editar -->
                                                    <a href="${pageContext.request.contextPath}/modulo?action=editar&idModulo=${modulo.id}&titulo=${modulo.titulo}&descricao=${modulo.descricao}" 
                                                            class="btn btn-sm btn-outline-primary me-2">
                                                            <i class="bi bi-pencil"></i> Editar
                                                        </a>

                                                        <!-- Gerenciar Vídeos -->
                                                        <a href="${pageContext.request.contextPath}/videoaula?action=novo&moduloId=${modulo.id}" 
                                                            class="btn btn-sm btn-outline-success me-2">
                                                            <i class="bi bi-camera-video"></i> Adicionar Vídeos
                                                        </a>

                                                        <!-- Excluir -->
                                                        <form action="${pageContext.request.contextPath}/modulo" method="post" class="d-inline"
                                                            onsubmit="return confirm('Tem certeza que deseja excluir este módulo e todos os seus vídeos?');">
                                                            <input type="hidden" name="id" value="${modulo.id}">
                                                            <input type="hidden" name="action" value="deletar">
                                                            <button type="submit" class="btn btn-sm btn-outline-danger">
                                                                <i class="bi bi-trash"></i> Excluir
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="text-center py-4">
                                    <i class="bi bi-folder-x text-muted" style="font-size: 3rem;"></i>
                                    <h4 class="h5 mt-3">Nenhum módulo cadastrado</h4>
                                    <p class="text-muted">Comece criando seu primeiro módulo usando o formulário acima</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Custom JS -->
    <script>
        // Validação do formulário
        document.getElementById('moduleForm').addEventListener('submit', function(e) {
            const titulo = document.getElementById('titulo').value.trim();
            const descricao = document.getElementById('descricao').value.trim();
            
            if (titulo === '' || descricao === '') {
                e.preventDefault();
                alert('Por favor, preencha todos os campos obrigatórios.');
                return false;
            }
        });
        
        // Foco no campo título quando em modo de edição
        <c:if test="${param.idModulo != null}">
            document.getElementById('titulo').focus();
        </c:if>
    </script>
</body>
</html>