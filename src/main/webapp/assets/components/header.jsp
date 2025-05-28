	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
<link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/static/css/header.css">

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a href="${pageContext.request.contextPath}/pages/home.jsp">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" height="55">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            
            <ul class="navbar-nav ms-3 me-auto mb-2 mb-lg-0">
                
                <li class="nav-item">
                    <a class="nav-link" href="#">Cursos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Nossa política </a>
                </li>
                
            </ul>
            <i class="icon bi-people-fill fs-4 text-white ms-4"></i>
        </div>
    </div>
</nav>
