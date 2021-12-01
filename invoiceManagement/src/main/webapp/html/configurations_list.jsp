<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>Listado de configuraciones</h1>
	<p>
		<a href="./new">&gt; Añadir nueva configuración</a>
	</p>
	<table class="table">
		<tr>
			<th>Año</th>
			<th>Saldo inicial</th>
			<th>Subvención</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${configurations}" var="curConfiguration">
			<tr>
				<td>${curConfiguration.year}</td>
				<td>${curConfiguration.initialBalance}</td>
				<td>${curConfiguration.subvention}</td>
				<td>
					<a href="./${curConfiguration.year}">&gt; show configuration</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>