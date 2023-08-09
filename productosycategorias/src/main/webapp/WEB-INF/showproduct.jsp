<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-4">

		<h1 class="text-center m-5 display-3"><u><c:out value="${producto.name }"></c:out></u></h1>

		<div class="row">
			<div class="col-md-6">
				<h3>Categorias</h3>
				<ul class="list-group">
					<c:forEach items="${producto.getCategories()}" var="categoria">
						<li class="list-group-item"><c:out value="${categoria.name }"></c:out></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-6">
				<h3>Add Category:</h3>
				<form:form action="/aso/${producto.id }" method="post"
					modelAttribute="asociacion">
					<form:input type="hidden" path="product" value="${producto.id }" />
					<form:select class="form-control" path="category">
						<c:forEach items="${categorias}" var="categoria">
							<form:option value="${categoria.id} "> ${categoria.name}  
									</form:option>
						</c:forEach>
					</form:select>
					<button  class="btn btn-outline-success m-3">Agregar</button>
				</form:form>


			</div>
		</div>
	</div>
</body>
</html>