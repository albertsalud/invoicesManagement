<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
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
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Formulario de factura</h1>
			<p><a href="<c:url value="/invoices" />" >&lt; Volver al listado de facturas</a></p>
			<form:form modelAttribute="invoiceFormDTO" method="post" action="save" enctype="multipart/form-data" >
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
				<p>
					Documento asociado: ${invoiceFormDTO.documentName}
				</p>
				<p>
					Nuevo documento: <form:input type="file" path="invoiceFile" accept="image/png, image/jpeg, application/pdf"/>
					<form:hidden path="documentName"/>
				</p>	
				<input type="submit" value="Guardar" class="boton" />
			</form:form>
		</div>
	</div>
</body>
</html>