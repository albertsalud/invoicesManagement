<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<script type="text/javascript">
function askForDelete(){
	return confirm("Se eliminará el elemento seleccionado. ¿Desea continuar?");
}

</script>
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Listado de facturas</h1>
			<p><a href="<c:url value="/invoices/new" />">&gt; Añadir nueva factura</a></p>
			<p><a href="<c:url value="/" />" >&lt; Volver al balance</a></p>
			<p>
				<jsp:include page="year-selector.jsp" >
					<jsp:param value="/invoices/" name="url"/>
				</jsp:include>
			</p>
			<table class="table" id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Fecha factura</th>
					<th>Proveedor</th>
					<th>Descripción</th>
					<th>Importe</th>
					<th>Pagador</th>
					<th>Fecha de abono</th>
					<th style="text-align: right;">
						<a target="_blank" href="<c:url value="/invoices/download?year=${selectedYear}" />" style="color: inherit;">&gt; Descargar facturas</a>
					</th>
				</tr>
				<c:forEach items="${invoicesList}" var="curInvoice">
					<tr>
						<td>
							<fmt:formatDate type ="time" value = "${curInvoice.invoiceDate}" pattern = "dd/MM/yyyy"/>
						</td>
						<td>${curInvoice.provider}</td>
						<td>${curInvoice.description}</td>
						<td class="currency">
							<fmt:formatNumber type = "currency" minFractionDigits = "2" value = "${curInvoice.amount}" />
						</td>
						<td>${curInvoice.payer}</td>
						<td>
							<fmt:formatDate type ="time" value = "${curInvoice.paymentDate}" pattern = "dd/MM/yyyy"/>
						</td>
						<td>
							<a href="<c:url value="/invoices/${curInvoice.id}" />">&gt; Modificar</a>
							<a onclick="return askForDelete()" href="<c:url value="/invoices/delete?invoice=${curInvoice.id}" />">&gt; Eliminar</a>
							<c:if test="${not empty curInvoice.documentName}">
								<a target="_blank" href="https://daudecinc.tk/uploaded/${curInvoice.year}/Factura/${curInvoice.documentName}">&gt; Ver factura</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>