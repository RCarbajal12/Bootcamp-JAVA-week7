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
<title>EDIT EVENTO</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5">
		<div class="text-end">
			<a href="/events" class="btn btn-outline-danger">Volver</a>
		</div>
		<h1 class=" mb-2 display-4">${evento.nombre }</h1>

		<div class="row m-3">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">Edit Event</div>
					<div class="card-body">
						<form:errors class="alert alert-danger" path="evento.*" />

						<form:form method="POST" action="/events/${evento.id}/edit"
							modelAttribute="evento">
							<input type="hidden" name="_method" value="put" />
							<form:input type="hidden" path="organizador"
								value="${usuario.id}" />

							<div class="mb-3">
								<form:label path="nombre" class="form-label">Event Name:</form:label>
								<form:input type="text" path="nombre" class="form-control" />
								<form:errors class="text-danger" path="nombre" />
							</div>

							<div class="mb-3">
								<form:label path="fecha" class="form-label">Event Date:</form:label>
								<form:input type="date" path="fecha" class="form-control" />
								<form:errors class="text-danger" path="fecha" />
							</div>

							<div class="row">
								<div class="col-md-6">
									<form:label path="ubicacion">Location:</form:label>
									<form:input type="text" path="ubicacion" class="form-control" />
								</div>
								<div class="col-md-6">
									<form:label path="provincia">State:</form:label>
									<form:select path="provincia" type="text" class="form-control">
										<c:forEach items="${provincias}" var="provincia">
											<form:option value="${provincia }"></form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<button type="submit" class="btn btn-primary my-3">Edit
								Event</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>