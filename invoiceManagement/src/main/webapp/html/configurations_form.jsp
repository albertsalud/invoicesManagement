<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Formulario de configuración</h1>
			<p><a href="<c:url value="/configurations" />" >&lt; Volver al listado de configuraciones</a></p>
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
				<input type="submit" value="Guardar" class="boton"/>
			</form:form>
		</div>
	</div>
</body>
</html>