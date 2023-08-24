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
	<div class="container text-center">
		<div class="m-4 text-end">
			<a href="/tres" class="btn btn-primary">Pregunta 3</a>
		</div>

		<h1 class="m-4">Pregunta 2:</h1>
		<h2 class="m-4">N�mero de Ciudades por Pa�s</h2>


		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Pa�s</th>
					<th>N�mero de Ciudades</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${segundaTabla}">
					<tr>
						<td>${row[0]}</td>
						<td>${row[1]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>