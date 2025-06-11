<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel= "stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
<meta charset="UTF-8">
<title>Capacita++</title>
<style type="text/css">

</style>
</head>
<body class="m-0 p-0">
	<nav class="navbar">
		<div class="container-nav">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/index.jsp"> <img
				src="${pageContext.request.contextPath}/static/images/logo.png"
				alt="Logo Capacita++" height="60">
			</a>
			
			<div class="nav-buttons">
				<a href="#sobre-nos" class="sem-botao">Sobre Nós</a>
				<a href="${pageContext.request.contextPath}/pages/cadastroAluno.jsp">Entre
					como estudante</a> <a
					href="${pageContext.request.contextPath}/pages/cadastroTutor.jsp">Entre
					como tutor</a> <a
					href="${pageContext.request.contextPath}/pages/login.jsp">Entrar</a>
			</div>
		</div>
	</nav>

	<section class="hero">
		<div class="hero-image">
		   <img src="${pageContext.request.contextPath}/static/images/imagemFundo.png" alt="Ilustração de inclusão educacional">
		</div>
		<div class="hero-content">
			<h1>Capacite-se para transformar realidades</h1>
			<p>Inclusão começa com conhecimento.</p>
			<p>Oferecemos capacitação para preparar profissionais para a
				diversidade e a inclusão no trabalho e na sociedade.</p>
			<div class="buton-hero">
				<a href="${pageContext.request.contextPath}/pages/login.jsp">Venha
					enriquecer seu conhecimento</a>
			</div>
		</div>
	</section>
	
	<section class="how-it-works">
	
		<h2>Como funciona a plataforma?</h2>

		<div class="steps">
			<div class="step">
				<i class="fas fa-user-plus step-icon"></i>
				<h3>Cadastre-se</h3>
				<p>Crie uma conta como estudante ou tutor para começar.</p>
			</div>

			<div class="step">
				<i class="fas fa-book-open step-icon"></i>
				<h3>Escolha um curso</h3>
				<p>Explore os cursos disponíveis e selecione o ideal.</p>
			</div>

			<div class="step">
				<i class="fas fa-video step-icon"></i>
				<h3>Assista às videoaulas</h3>
				<p>Aprenda com especialistas no seu ritmo.</p>
			</div>

			<div class="step">
				<i class="fas fa-comments step-icon"></i>
				<h3>Comente e interaja</h3>
				<p>Participe com perguntas e sugestões para enriquecer sua
					jornada.</p>
			</div>
		</div>
	</section>

	<section class="top-courses">
		<h2> EAD: confira os cursos mais acessados</h2>
		<div class="course-cards">
			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/libras.png" alt="Curso 1">
				<p>capacitação básica em libras </p>
			</div>
			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/diferença.webp" alt="Curso 2">
				<p>Educação especial x Educação inclusiva: entendea as diferenças</p>
			</div>
			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/educacao.jpeg" alt="Curso 3">
				<p>Como a educação especial e a educação inclusiva caminham juntas</p>
			</div>
			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/capa.jpeg" alt="Curso 4">
				<p>Aprendar a elaborar PEI e PDI no AEE</p>
			</div>
			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/braile.jpg" alt="Capacitação em braille">
				<p>Capacitação em braille</p> 
	        </div>
	</div>
      </section>

	</section> <section class="related-articles">
		<h2>Entenda sobre A importancia da Inclusão</h2> <div class="course-cards"> <div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/educacao inclusiva.jpeg" alt="Ilustração do artigo: Educação Inclusiva">
				<p>Educação Inclusiva: Entre a História, os Preconceitos, a Escola e a Família</p>
				<a href="https://www.scielo.br/j/pcp/a/gtPdzXy4yHrX9Lz9txCtQ7c" class="link-no-card">Leia Mais</a>
			</div>

			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/incluso.jpeg" alt="Ilustração sobre inclusão como direito">
				<p>Inclusão: um direito de todos</p>
				<a href="https://educacaopublica.cecierj.edu.br/artigos/20/29/inclusao-um-direito-de-todos" class="link-no-card">Leia Mais</a>
			</div>

			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/promovendo educacao.jpeg" alt="Ilustração sobre suporte teórico em educação inclusiva">
				<p>A Educação Inclusiva e Seu Suporte Teórico Documental</p>
				<a href="https://periodicos.furg.br/momento/article/view/15986" class="link-no-card">Leia Mais</a>
			</div>

			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/suporteEducacao.jpeg" alt="Ilustração sobre desafios e estratégias em educação inclusiva">
				<p>Promovendo a Educação Inclusiva: Desafios e Estratégias</p>
				<a href="https://ojs.focopublicacoes.com.br/foco/article/view/5598" class="link-no-card">Leia Mais</a>
			</div>

			<div class="course-card">
				<img src="${pageContext.request.contextPath}/static/images/educacao1.jpeg" alt="Ilustração genérica sobre educação inclusiva">
				<p>Educação inclusiva</p>
				<a href="https://brasilescola.uol.com.br/educacao/educacao-inclusiva.htm" class="link-no-card">Leia Mais</a>
			</div>

		</div>
	</section>

	<section id="sobre-nos" class="sobre-nos">

	  <section id="sobre-nos" class="sobre-nos">
		<h2>Sobre Nós</h2>
		<p>
			Este sistema foi desenvolvido como parte da disciplina de <strong>Programação Orientada a Objetos (POO)</strong>, com o objetivo de aplicar os conhecimentos adquiridos durante o curso de Análise e Desencvolvimento de Sistemas, focando na criação de uma <strong>plataforma educacional inclusiva</strong>.
		</p>

		<p>
		<strong>Integrantes:</strong><br>
		Farley Souza Silva de Oliveira<br>
		Geraldo Rafael Lopes Benevides<br>
		Julia Evelyn Magalhães dos Santos<br>
		Maíra Ramos Teixeira<br>
		Tainara do Amaral Oliveira Azevedo
		</p>
		<p>
			<strong>Orientador:</strong><br>
			Prof. Woquiton Lima Fernandes
		</p>
		<p>
			<strong>Instituição:</strong><br>
			Intituto Federal de Educação, Ciência e Tecnologia Baiano - Campus Guanambi
		</p>
		<p>
			<strong>Curso:</strong> Análise e Desencvolvimento de Sistemas<br>
			<strong>Semestre:</strong> 3º semestre de 2025
		</p><br><br>
	  </section>
	  
			
			<footer>
				<p>&copy; 2025 Capacita++. Todos os direitos reservados.</p>
			</footer>
</body>
</html>