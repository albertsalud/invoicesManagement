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
	$( "#invoiceDate" ).datepicker({
		dateFormat: "dd/mm/yy"
	});
	$( "#paymentDate" ).datepicker({
		dateFormat: "dd/mm/yy"
	});
} );
</script>

<title>Insert title here</title>
</head>
<body>
	<h1>Formulario de factura</h1>
	<form:form modelAttribute="invoiceFormDTO" method="post" action="save" >
		<form:hidden path="id" />
		<p>
			Fecha factura: <form:input path="invoiceDate" />
			<form:errors path="invoiceDate" />
		</p>
		<p>
			Núm. factura: <form:input path="invoiceNumber" />
			<form:errors path="invoiceNumber" />
		</p>
		<p>
			Proveedor: <form:input path="provider" />
			<form:errors path="provider" />
		</p>
		<p>
			Cantidad: <form:input path="amount" />
			<form:errors path="amount" />
		</p>
		<p>
			Pagador: <form:input path="payer" />
			<form:errors path="payer" />
		</p>
		<p>
			Fecha de abono: <form:input path="paymentDate" />
		</p>
		<p>
			Descripción: <form:input path="description" />
			<form:errors path="description" />
		</p>
		<p>
			Afecta al saldo: <form:checkbox path="balanceDiscount" />
		</p>
		<input type="submit" value="Guardar" />
	</form:form>
</body>
</html>