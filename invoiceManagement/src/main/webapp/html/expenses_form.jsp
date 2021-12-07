<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css" />
<script>
$( function() {
	$( "#expenseDate" ).datepicker({
		dateFormat: "dd/mm/yy"
	});
} );
</script>

<title>Insert title here</title>
</head>
<body>
	<h1>Formulario de gasto</h1>
	<ul>
		<li><a href="<c:url value="/expenses" />" >&lt; Volver al listado de gastos</a></li>
	</ul>
	<form:form modelAttribute="expenseFormDTO" method="post" action="save" >
		<form:hidden path="id" />
		<p>
			Fecha gasto: <form:input path="expenseDate" />
			<form:errors path="expenseDate" />
		</p>
		<p>
			Descripción: <form:input path="description" />
			<form:errors path="description" />
		</p>
		<p>
			Cantidad: <form:input path="amount" />
			<form:errors path="amount" />
		</p>
		<input type="submit" value="Guardar" />
	</form:form>
</body>
</html>