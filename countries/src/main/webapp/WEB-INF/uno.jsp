<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregunta Uno</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<div class="m-4 text-end">
			<a href="/dos" class="btn btn-primary">Pregunta 2</a>
		</div>

		<h1 class="text-center">Pregunta Uno:</h1>
		<h2 class="text-center m-3">Países que hablan Sloveno</h2>


		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Countries</th>
					<th>Language</th>
					<th>Percentage</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="columna" items="${primeraTabla}">
					<tr>
						<td>${columna[0]}</td>
						<td>${columna[1]}</td>
						<td>${columna[2]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>