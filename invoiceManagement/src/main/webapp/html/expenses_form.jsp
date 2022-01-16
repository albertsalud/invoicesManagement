<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<script>
$( function() {
	$( "#expenseDate" ).datepicker({
		dateFormat: "dd/mm/yy"
	});
} );
</script>
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Formulario de gasto</h1>
			<p><a href="<c:url value="/expenses" />" >&lt; Volver al listado de gastos</a></p>
			<form:form modelAttribute="expenseFormDTO" method="post" action="save" enctype="multipart/form-data" >
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
				<p>
					Documento asociado: ${expenseFormDTO.documentName}
				</p>
				<p>
					Subir documento: <form:input type="file" path="expenseFile" accept="image/png, image/jpeg, application/pdf"/>
					<form:hidden path="documentName"/>
				</p>		
				<input type="submit" value="Guardar" class="boton"/>
			</form:form>
		</div>
	</div>
</body>
</html>