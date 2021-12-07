<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Formulario de configuración</h1>
	<ul>
		<li><a href="<c:url value="/configurations" />" >&lt; Volver al listado de configuraciones</a></li>
	</ul>
	<c:url value="/configurations/save" var="formURL" />
	<form:form modelAttribute="configuration" method="post" action="${formURL}" >
		<p>
			Año: <form:input path="year" />
			<form:errors path="year" />
		</p>
		<p>
			Saldo inicial: <form:input path="initialBalance" />
			<form:errors path="initialBalance" />
		</p>
		<p>
			Subvención: <form:input path="subvention" />
			<form:errors path="subvention" />
		</p>
		<input type="submit" value="Guardar" />
	</form:form>
</body>
</html>