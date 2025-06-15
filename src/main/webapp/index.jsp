<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Capacita++</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel= "stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">

</head>
<body>
    <!-- NAVBAR -->
    <nav class="navbar">
        <div class="container container-nav">
            <a class="navbar-brand" href="#">
                <img src="static/imagens/logo.png" width='100' height='60' viewBox='0 0 100 60'><rect width='100' height='60' fill='%231e3c72'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='24' fill='white'>Capacita++</text></a>
            
            <div class="nav-buttons">
                <a href="#sobre-nos" class="sem-botao">Sobre N�s</a>
                <a href="#">Entre como estudante</a>
                <a href="#">Entre como tutor</a>
                <a href="#">Entrar</a>
            </div>
        </div>
    </nav>

    <!-- HERO SECTION -->
    <section class="hero">
        <div class="container" style="display: flex; align-items: center;">
            <div class="hero-content">
                <h1>Capacite-se para transformar realidades</h1>
                <p>Inclus�o come�a com conhecimento.</p>
                <p>Oferecemos capacita��o para preparar profissionais para a diversidade e a inclus�o no trabalho e na sociedade.</p>
                <div class="hero-buttons">
                    <a href="#" class="btn btn-primary">Comece Agora</a>
                    <a href="#sobre-nos" class="btn btn-outline">Saiba Mais</a>
                </div>
            </div>
            <div class="hero-image">
                <img src="static/images/fundo.png" width='800' height='500' viewBox='0 0 500 400'><rect width='500' height='500' fill='%234a90e2' opacity='0.2' rx='20'/><circle cx='250' cy='150' r='80' fill='%231e3c72' fill-opacity='0.7'/><rect x='150' y='250' width='200' height='100' fill='%231e3c72' fill-opacity='0.7' rx='10'/><circle cx='200' cy='120' r='20' fill='white'/><circle cx='300' cy='120' r='20' fill='white'/><path d='M 200 200 Q 250 230 300 200' stroke='white' stroke-width='8' fill='none'/>
            </div>
        </div>
    </section>

    <!-- HOW IT WORKS -->
    <section class="section how-it-works">
        <div class="container">
            <div class="section-title">
                <h2>Como funciona a plataforma?</h2>
            </div>
            
            <div class="steps">
                <div class="step">
                    <div class="step-icon">
                        <i class="fas fa-user-plus"></i>
                    </div>
                    <h3>Cadastre-se</h3>
                    <p>Crie uma conta como estudante ou tutor para come�ar.</p>
                </div>
                
                <div class="step">
                    <div class="step-icon">
                        <i class="fas fa-book-open"></i>
                    </div>
                    <h3>Escolha um curso</h3>
                    <p>Explore os cursos dispon�veis e selecione o ideal.</p>
                </div>
                
                <div class="step">
                    <div class="step-icon">
                        <i class="fas fa-video"></i>
                    </div>
                    <h3>Assista �s videoaulas</h3>
                    <p>Aprenda com especialistas no seu ritmo.</p>
                </div>
                
                <div class="step">
                    <div class="step-icon">
                        <i class="fas fa-comments"></i>
                    </div>
                    <h3>Comente e interaja</h3>
                    <p>Participe com perguntas e sugest�es para enriquecer sua jornada.</p>
                </div>
            </div>
        </div>
    </section>

    <!-- TOP COURSES -->
    <section class="section top-courses">
        <div class="container">
            <div class="section-title">
                <h2>Confira os cursos mais acessados</h2>
            </div>
            
            <div class="course-cards">
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%234a90e2'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Libras</text></svg>" alt="Curso 1">
                    <p>Capacita��o b�sica em libras</p>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%232a5298'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Educa��o Especial</text></svg>" alt="Curso 2">
                    <p>Educa��o especial x Educa��o inclusiva: entenda as diferen�as</p>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%231e3c72'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Educa��o</text></svg>" alt="Curso 3">
                    <p>Como a educa��o especial e a educa��o inclusiva caminham juntas</p>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%234a90e2'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>PEI e PDI</text></svg>" alt="Curso 4">
                    <p>Aprenda a elaborar PEI e PDI no AEE</p>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%232a5298'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Braille</text></svg>" alt="Capacita��o em braille">
                    <p>Capacita��o em braille</p>
                </div>
            </div>
        </div>
    </section>

    <!-- RELATED ARTICLES -->
    <section class="section related-articles">
        <div class="container">
            <div class="section-title">
                <h2>Entenda sobre a import�ncia da Inclus�o</h2>
            </div>
            
            <div class="course-cards">
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%231e3c72'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Educa��o Inclusiva</text></svg>" alt="Ilustra��o do artigo: Educa��o Inclusiva">
                    <p>Educa��o Inclusiva: Entre a Hist�ria, os Preconceitos, a Escola e a Fam�lia</p>
                    <a href="#" class="link-no-card">Leia Mais</a>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%232a5298'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Inclus�o</text></svg>" alt="Ilustra��o sobre inclus�o como direito">
                    <p>Inclus�o: um direito de todos</p>
                    <a href="#" class="link-no-card">Leia Mais</a>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%234a90e2'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Suporte Te�rico</text></svg>" alt="Ilustra��o sobre suporte te�rico em educa��o inclusiva">
                    <p>A Educa��o Inclusiva e Seu Suporte Te�rico Documental</p>
                    <a href="#" class="link-no-card">Leia Mais</a>
                </div>
                
                <div class="course-card">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='220' height='140' viewBox='0 0 220 140'><rect width='220' height='140' fill='%231e3c72'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='16' fill='white'>Estrat�gias</text></svg>" alt="Ilustra��o sobre desafios e estrat�gias em educa��o inclusiva">
                    <p>Promovendo a Educa��o Inclusiva: Desafios e Estrat�gias</p>
                    <a href="#" class="link-no-card">Leia Mais</a>
                </div>
            </div>
        </div>
    </section>

    <!-- SOBRE N�S -->
    <section id="sobre-nos" class="section sobre-nos">
        <div class="container">
            <div class="section-title">
                <h2>Sobre N�s</h2>
            </div>
            
            <div class="about-content">
                <p>Este sistema foi desenvolvido como parte da disciplina de <strong>Programa��o Orientada a Objetos (POO)</strong>, com o objetivo de aplicar os conhecimentos adquiridos durante o curso de An�lise e Desenvolvimento de Sistemas, focando na cria��o de uma <strong>plataforma educacional inclusiva</strong>.</p>
                
                <div class="about-grid">
                    <div class="about-item">
                        <h3>Integrantes</h3>
                        <p><i class="fas fa-user"></i> Ma�ra Ramos Teixeira</p>
                        <p><i class="fas fa-user"></i> Tainara do Amaral Oliveira Azevedo</p>
                    </div>
                    
                    <div class="about-item">
                        <h3>Orientador</h3>
                        <p><i class="fas fa-chalkboard-teacher"></i> Prof. Woquiton Lima Fernandes</p>
                    </div>
                    
                    <div class="about-item">
                        <h3>Institui��o</h3>
                        <p><i class="fas fa-university"></i> Instituto Federal de Educa��o, Ci�ncia e Tecnologia Baiano</p>
                        <p><i class="fas fa-map-marker-alt"></i> Campus Guanambi</p>
                    </div>
                    
                    <div class="about-item">
                        <h3>Curso</h3>
                        <p><i class="fas fa-graduation-cap"></i> An�lise e Desenvolvimento de Sistemas</p>
                        <p><i class="fas fa-calendar-alt"></i> 3� semestre de 2025</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- FOOTER -->
    <footer>
        <div class="container">
            <div class="footer-content">
                <div class="footer-logo">
                    <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='100' height='60' viewBox='0 0 100 60'><rect width='100' height='60' fill='white' opacity='0.1' rx='5'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='14' fill='white'>Capacita++</text></svg>" alt="Logo Capacita++">
                    <p>Sua plataforma de capacita��o para a inclus�o educacional.</p>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        <a href="#"><i class="fab fa-youtube"></i></a>
                    </div>
                </div>
                
                <div class="footer-links">
                    <h3>Links R�pidos</h3>
                    <ul>
                        <li><a href="#">P�gina Inicial</a></li>
                        <li><a href="#sobre-nos">Sobre N�s</a></li>
                        <li><a href="#">Cursos</a></li>
                        <li><a href="#">Artigos</a></li>
                        <li><a href="#">Contato</a></li>
                    </ul>
                </div>
                
                <div class="footer-links">
                    <h3>Cursos Populares</h3>
                    <ul>
                        <li><a href="#">Capacita��o em Libras</a></li>
                        <li><a href="#">Educa��o Especial x Inclusiva</a></li>
                        <li><a href="#">Capacita��o em Braille</a></li>
                        <li><a href="#">Elabora��o de PEI e PDI</a></li>
                    </ul>
                </div>
            </div>
            
            <div class="copyright">
                <p>&copy; 2025 Capacita++. Todos os direitos reservados.</p>
            </div>
        </div>
    </footer>
</body>
</html>