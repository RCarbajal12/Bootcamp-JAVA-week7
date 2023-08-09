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
<title>Category Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<div class="container mt-4">
        <h1 class="text-center m-5 display-3"><u>${categoria.name}</u></h1>
        <div class="row">
            <div class="col-md-6">
                <h3>Productos:</h3>
                <ul class="list-group">
                    <c:forEach items="${categoria.getProducts()}" var="producto">
                        <li class="list-group-item">${producto.name}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6">
                <h3>Agregar Producto:</h3>
                <form:form action="/ase/${categoria.id}" method="post" modelAttribute="asociaciones">
                    <form:input type="hidden" path="category" value="${categoria.id}" />
                    <div class="form-group">
                        <form:select class="form-control" path="product">
                            <c:forEach items="${productos}" var="producto">
                                <form:option value="${producto.id}">${producto.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <button class="btn btn-outline-success m-3">Agregar</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>