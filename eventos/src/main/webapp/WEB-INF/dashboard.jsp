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
<title>APP</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css"> -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5 ">
		<div class="text-end">
			<a href="/logout" class="btn btn-outline-danger">Logout</a>
		</div>
		<h1 class="display-3">
			Welcome,
			<c:out value="${usuario.nombre}"></c:out>
		</h1>

		<hr>

		<div class="row m-2">
			<div class="col-md-12">
				<h3>Here are some events in your state:</h3>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Name</th>
								<th>Date</th>
								<th>Location</th>
								<th>Province</th>
								<th>Host</th>
								<th>Action/Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${eventosPronvinciaUser}" var="eventoUser">
								<tr>
									<td><a href="/events/${eventoUser.id}"
										class="text-primary"><c:out value="${eventoUser.nombre}"></c:out></a></td>
									<td><fmt:formatDate value="${eventoUser.fecha}"
											pattern="MMMM dd, yyyy" var="fechaFormateada" /> <c:out
											value="${fechaFormateada}"></c:out></td>
									<td><c:out value="${eventoUser.ubicacion}"></c:out></td>
									<td><c:out value="${eventoUser.provincia}"></c:out></td>
									<td><c:out value="${eventoUser.organizador.nombre} ${eventoUser.organizador.apellido}"></c:out></td>
									<td><c:choose>
											<c:when test="${eventoUser.organizador.id == usuario.id}">
												<a href="/events/${eventoUser.id}/edit"
													class="btn btn-sm btn-outline-primary">Edit</a>
												<form:form action="/events/${eventoUser.id}/delete"
													method="post" class="d-inline">
													<input type="hidden" name="_method" value="delete" />
													<button class="btn btn-sm btn-outline-danger">Delete</button>
												</form:form>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${eventoUser.asistentes.contains(usuario)}">
														<a href="/event/${eventoUser.id}/${usuario.id}/cancelar"
															class="btn btn-sm btn-outline-danger">Cancelar</a>
													</c:when>
													<c:otherwise>
														<a href="/event/${eventoUser.id}/${usuario.id}/unirse"
															class="btn btn-sm btn-outline-success">Unirse</a>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<hr>

		<div class="row m-2">
			<div class="col-md-12">
				<h3>Here are some events in other states:</h3>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Name</th>
								<th>Date</th>
								<th>Location</th>
								<th>Province</th>
								<th>Host</th>
								<th>Action/Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${eventosNoPronvinciaUser}" var="otroseventos">
								<tr>
									<td><a href="/events/${otroseventos.id}"
										class="text-primary"><c:out
												value="${otroseventos.nombre }"></c:out></a></td>
									<td><fmt:formatDate value="${otroseventos.fecha}"
											pattern="MMMM dd, yyyy" var="fechaFormateada" /> <c:out
											value="${fechaFormateada}"></c:out></td>
									<td><c:out value="${otroseventos.ubicacion}"></c:out></td>
									<td><c:out value="${otroseventos.provincia}"></c:out></td>
									<td><c:out value="${otroseventos.organizador.nombre} ${otroseventos.organizador.apellido}"></c:out></td>
									<td><c:choose>
											<c:when test="${otroseventos.organizador.id == usuario.id}">
												<a href="/events/${otroseventos.id}/edit"
													class="btn btn-sm btn-outline-primary">Edit</a>
												<a href="/events/${otroseventos.id}/delete"
													class="btn btn-sm btn-outline-danger">Delete</a>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${otroseventos.asistentes.contains(usuario)}">
														<a href="/event/${otroseventos.id}/${usuario.id}/cancelar"
															class="btn btn-sm btn-outline-danger">Cancelar</a>
													</c:when>
													<c:otherwise>
														<a href="/event/${otroseventos.id}/${usuario.id}/unirse"
															class="btn btn-sm btn-outline-success">Unirse</a>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row m-2">
			<div class="col-md-6">
				<h4>Create an event!</h4>
				<form:errors class="text-danger" path="evento.*" />
				<form:form method="POST" action="/nuevo/evento"
					modelAttribute="evento">
					<form:input type="hidden" path="organizador" value="${usuario.id}" />
					<div class="form-group col-md-8">
						<form:label path="nombre">Event Name:</form:label>
						<form:input type="text" path="nombre" class="form-control" />
						<form:errors class="text-danger" path="nombre" />
					</div>
					<div class="form-row">
						<div class="form-group col-md-8">
							<form:label path="fecha">Event date:</form:label>
							<form:input type="date" path="fecha" class="form-control" />
							<form:errors class="text-danger" path="fecha" />
						</div>
						<div class="form-group col-md-8">
							<div class="row">
								<div class="col-md-8">
									<form:label path="ubicacion">Location:</form:label>
									<form:input type="text" path="ubicacion" class="form-control" />
									<form:errors class="text-danger" path="ubicacion" />
								</div>
								<div class="col-md-4">
									<form:label path="provincia">State:</form:label>
									<form:select path="provincia" type="text" class="form-control">
										<c:forEach items="${provincias}" var="provincia">
											<form:option value="${provincia }"></form:option>
										</c:forEach>
									</form:select>
									<form:errors class="text-danger" path="provincia" />
								</div>
							</div>
							<input type="submit" value="Create" class="btn btn-success my-2" />
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>