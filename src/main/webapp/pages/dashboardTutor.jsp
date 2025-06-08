<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    Tutor tutor = (Tutor) session.getAttribute("tutor");
    if (tutor == null) {
        System.out.println("Sessão não contém o atributo 'tutor'. Redirecionando para login.");
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Portal tutor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <style type="text/css">
    body {
	color: #3465a4;
}

.btn-outline-secondary {
	border-color: #e9ecef;
	color: #e9ecef;
}
.custom-navbar {
	background-color: #084673 !important;
	color: white !important; 
}

.btn-custom-width {
	width: 95%;
}
.bnt-card{
	color: #084673;
	border-radius:25px;
}

.img-card {
    height: 140px;
    object-fit: cover;
}

.offcanvas {
   color:#3465a4 ;
}
    
    </style>
</head>
<body>
 <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
            <button class="btn btn-outline-secondary me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral" aria-label="Abrir menu lateral">
                <i class="bi bi-list fs-3"></i>
            </button>
            <a href="${pageContext.request.contextPath}/pages/homeTutor.jsp" class="navbar-brand">
               <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" height="65" /> 
            </a>
        </div>
    </nav>
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="menuLateralLabel">Menu do Tutor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Fechar"></button>
        </div>
        <div class="offcanvas-body">
            <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/formularioCadastroVideoAula.jsp"><i class="bi bi-folder-plus me-2"></i>Adicionar novo Modulo de aula</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/perfiltutor.jsp"><i class="bi bi-person-circle me-2"></i>Meu Perfil</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/formularioCadastroVideoAula.jsp"><i class="bi bi-camera-video me-2"></i>Nova aula</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/pages/listarvideoaulaTutor.jsp"><i class="bi bi-folder-plus me-2"></i>Cursos postados</a></li>
                <li><hr /></li>
                <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/index.jsp"><i class="bi bi-box-arrow-right me-2"></i>Sair</a></li>
            </ul>
        </div>
    </div>
            <h1 class="main-title text-center mb-4">Painel de gerenciamento de aulas</h1>
   
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    
</body>
</html>
