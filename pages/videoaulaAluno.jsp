<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Aluno" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>


<%

    //BLOCO DE TESTE (REMOVER)
//    HttpSession sessao = request.getSession();

  //  sessao.removeAttribute("usuarioLogado"); // Forçar remoção de qualquer "usuarioLogado" antigo para evitar conflito Tutor x Aluno
    //if(sessao.getAttribute("usuarioLogado") == null){

    //Calendar cal = Calendar.getInstance();
    //cal.set(2003, Calendar.JANUARY, 1);
    //java.util.Date utilDate = cal.getTime();
    //ja/va.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    //com.ifbaiano.educacaoinclusiva.model.Aluno usuarioTeste = 
   // new com.ifbaiano.educacaoinclusiva.model.Aluno(
     //   1, "Aluno Teste", "alunoteste@gmail.com", "987654", "Biografia teste para aluno teste", sqlDate
    //);

       // sessao.setAttribute("usuarioLogado", usuarioTeste);
   // }
    //FIM DO BLOCO DE TESTE

%>

<%
    Aluno aluno = (Aluno) sessao.getAttribute("usuarioLogado");
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel de Vídeo Aulas</title>
</head>
<body>

<h1>Seja Bem-vindo, <%= aluno.getRetornaNome() %>!</h1>
<p>Email: <%= aluno.getEmail() %></p>

<h2>Menu de Vídeo Aulas</h2>
<ul>
    <li><a href="listarvideoaulaAluno.jsp">Visualizar todas as video aulas</a></li>
    <li><a href="logout.jsp">Sair</a></li>
</ul>

</body>
</html>
