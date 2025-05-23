<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario" %>


<nav class="fixed-top navbar navbar-dark bg-dark navbar-expand-lg">
  
        <a class="navbar-brand me-5" href="#">Educacao Inclusiva</a>

        <form action="${pageContext.request.contextPath}/search" method="post" class="d-flex justify-content-center">
        <div class="input-group ">
            <input type="text" class="form-control" name="pesquisa" placeholder="Buscar" required/>
            <button type="submit" class="btn btn-success">A</button>
        </div>
        </form>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
            </li>
