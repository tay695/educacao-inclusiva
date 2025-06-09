<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.VideoAula" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Modulo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
VideoAula video = (VideoAula) request.getAttribute("video");
List<Modulo> modulos = (List<Modulo>) request.getAttribute("modulos");
int idModuloSelecionado = request.getAttribute("idModuloSelecionado") != null
        ? (Integer) request.getAttribute("idModuloSelecionado")
        : 0;
String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <!-- Cabeçalho mantido igual -->
</head>
<body>
    <nav class="navbar navbar-expand-lg custom-navbar bg-light">
        <!-- Menu lateral mantido igual -->
    </nav>

    <main class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <h1 class="text-center mb-4">${video == null ? 'Cadastrar' : 'Editar'} vídeo-aula</h1>
                
                <!-- Mensagem de módulo selecionado -->
                <c:if test="${idModuloSelecionado > 0}">
                    <div class="alert alert-info mb-4">
                        <i class="bi bi-info-circle"></i> Você está adicionando vídeos ao módulo:
                        <strong>
                            <c:forEach items="${modulos}" var="modulo">
                                <c:if test="${modulo.id == idModuloSelecionado}">${modulo.titulo}</c:if>
                            </c:forEach>
                        </strong>
                    </div>
                </c:if>

                <div class="card shadow-sm">
                    <div class="card-body">
                        <form action="${ctx}/videoaula" method="post" id="videoForm">
                            <input type="hidden" name="action" value="${video == null ? 'create' : 'update'}">
                            <c:if test="${video != null}">
                                <input type="hidden" name="id" value="${video.id}">
                            </c:if>

                            <!-- Título -->
                            <div class="mb-4">
                                <label for="titulo" class="form-label fw-bold">Título da vídeo-aula:</label>
                                <input type="text" class="form-control form-control-lg" name="titulo" id="titulo" required
                                    value="${video != null ? video.titulo : ''}"
                                    placeholder="Digite um título descritivo para a vídeo-aula">
                                <div class="form-text">Ex: "Introdução à Educação Inclusiva - Parte 1"</div>
                            </div>
                            
                            <!-- URL do Vídeo -->
                            <div class="mb-4">
                                <label for="url" class="form-label fw-bold">Link da vídeo-aula:</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-link-45deg"></i></span>
                                    <input type="url" class="form-control form-control-lg" name="url" id="url"
                                        placeholder="https://www.youtube.com/embed/..." required
                                        value="${video != null ? video.url : ''}">
                                </div>
                                <div id="urlHelp" class="form-text">Cole o link de incorporação do YouTube</div>
                            </div>
                            
                            <!-- Módulo -->
                            <div class="mb-4">
                                <label for="idModulo" class="form-label fw-bold">Módulo associado:</label>
                                <c:choose>
                                    <c:when test="${empty modulos}">
                                        <div class="alert alert-warning">
                                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                            Nenhum módulo disponível. 
                                            <a href="${ctx}/modulo?action=novo&redirect=videoaula" class="alert-link">
                                                Clique aqui para criar um módulo
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <select class="form-select form-select-lg" name="idModulo" id="idModulo" required
                                            ${idModuloSelecionado > 0 ? 'disabled' : ''}>
                                            <option value="">Selecione um módulo</option>
                                            <c:forEach items="${modulos}" var="modulo">
                                                <option value="${modulo.id}"
                                                    ${(video != null && video.modulo != null && video.modulo.id == modulo.id) 
                                                      || modulo.id == idModuloSelecionado ? 'selected' : ''}>
                                                    ${modulo.titulo}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <c:if test="${idModuloSelecionado > 0}">
                                            <input type="hidden" name="idModulo" value="${idModuloSelecionado}">
                                        </c:if>
                                        <div class="form-text">Selecione o módulo ao qual esta vídeo-aula pertence</div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <!-- Botões de ação -->
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                                <a href="${ctx}/pages/listarvideoaulaTutor.jsp" class="btn btn-outline-secondary me-md-2">
                                    <i class="bi bi-arrow-left me-2"></i>Cancelar
                                </a>
                                <button type="submit" class="btn btn-primary" id="submitBtn">
                                    <i class="bi bi-save me-2"></i>${video == null ? 'Cadastrar' : 'Salvar'} vídeo-aula
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                
            </div>
        </div>
    </main>


    <!-- Modal de novo módulo -->
    <div class="modal fade" id="novoModuloModal" tabindex="-1" aria-labelledby="novoModuloModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="novoModuloModalLabel">Criar Novo Módulo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="quickModuloForm" action="${ctx}/modulo" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="action" value="create">
                        <input type="hidden" name="redirect" value="videoaula">
                        
                        <div class="mb-3">
                            <label for="moduloTitulo" class="form-label">Título do Módulo</label>
                            <input type="text" class="form-control" id="moduloTitulo" name="titulo" required>
                        </div>
                        <div class="mb-3">
                            <label for="moduloDescricao" class="form-label">Descrição</label>
                            <textarea class="form-control" id="moduloDescricao" name="descricao" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Salvar Módulo</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Validação do formulário
        document.getElementById('videoForm').addEventListener('submit', function(e) {
            const urlInput = document.getElementById('url');
            const urlValue = urlInput.value.trim();
            
            if (!urlValue.includes('youtube.com/embed/') && !urlValue.includes('youtu.be/')) {
                e.preventDefault();
                alert('Por favor, insira um link de incorporação válido do YouTube (deve conter youtube.com/embed/ ou youtu.be/)');
                urlInput.focus();
                return false;
            }
            
            // Feedback visual durante o envio
            const submitBtn = document.getElementById('submitBtn');
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Processando...';
        });

        // Adicionar botão de novo módulo dinamicamente
        document.addEventListener('DOMContentLoaded', function() {
            const moduloLabel = document.querySelector('label[for="idModulo"]');
            if (moduloLabel) {
                const btnNovoModulo = document.createElement('button');
                btnNovoModulo.className = 'btn btn-outline-primary btn-sm ms-2';
                btnNovoModulo.innerHTML = '<i class="bi bi-plus-lg me-1"></i> Criar Novo Módulo';
                btnNovoModulo.type = 'button';
                btnNovoModulo.setAttribute('data-bs-toggle', 'modal');
                btnNovoModulo.setAttribute('data-bs-target', '#novoModuloModal');
                moduloLabel.appendChild(btnNovoModulo);
            }
        });
    </script>
</body>
</html>