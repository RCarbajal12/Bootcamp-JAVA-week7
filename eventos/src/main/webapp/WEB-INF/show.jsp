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
<title><c:out value="${evento.nombre }"></c:out></title>
<style>
/* Tu código CSS aquí */
.message-list {
	max-height: 200px;
	overflow-y: auto;
}
</style>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-12">
				<div class="text-end">
					<a href="/events" class="btn btn-outline-danger">Volver</a>
				</div>
				<h1 class="display-2 my-3 text-center font-weight-bold text-primary">
					<c:out value="${evento.nombre }!"></c:out>
				</h1>
			</div>
		</div>

		<div class="row m-3">
			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">Detalles del Evento</div>
					<div class="card-body">
						<p class="card-title">Host: ${evento.organizador.nombre }
							${evento.organizador.apellido }</p>
						<p class="card-text">
							Fecha:
							<fmt:formatDate value="${evento.fecha }" pattern="MMMM dd, yyyy"
								var="fechaFormateada" />
							<c:out value="${fechaFormateada}"></c:out>
						</p>
						<p class="card-text">Locación: ${evento.ubicacion }</p>
						<p class="card-text">Asistentes totales:
							${evento.asistentes.size() }</p>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<h5 class="mb-0">Asistentes:</h5>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Nombre</th>
										<th>Location</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="asistente" items="${evento.asistentes}">
										<tr>
											<td>${asistente.nombre}${asistente.apellido}</td>
											<td>${asistente.ubicacion}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">Muro de Mensajes</div>
					<div class="card-body message-wall">
						<ul class="list-group message-list">
							<c:forEach var="mensaje" items="${evento.mensajes}">
								<li class="list-group-item"><strong>${mensaje.autor.nombre}
										${mensaje.autor.apellido}:</strong> ${mensaje.comentario }</li>
							</c:forEach>
						</ul>

						<form action="/events/${evento.id }/comentario" method="post">
							<div class="form-group m-3">
								<label for="comment">Agregar comentario</label>
								<textarea name="comment" id="comment" class="form-control"></textarea>
							</div>
							<button type="submit" class="btn btn-primary m-2">Enviar
								Comentario</button>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>