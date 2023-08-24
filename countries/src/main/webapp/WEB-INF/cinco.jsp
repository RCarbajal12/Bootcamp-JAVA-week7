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
<title>AQUI EL TITULO DE TU PLNATILLA</title>
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
			<a href="/seis" class="btn btn-primary">Pregunta 6</a>
		</div>
		<h1 class="text-center m-4">Pregunta 5:</h1>
		<h2 class="text-center m-4">Países con un superficie de área inferior a 501 y una población mayor a 100.000</h2>

		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>País</th>
					<th>Superficie</th>
					<th>Población</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${quintaTabla}">
					<tr>
						<td>${row[0]}</td>
						<td>${row[1]}</td>
						<td>${row[2]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>