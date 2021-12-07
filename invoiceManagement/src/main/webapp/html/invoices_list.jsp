<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>Listado de facturas</h1>
	<ul>
		<li><a href="<c:url value="/invoices/new" />">&gt; Añadir nueva factura</a></li>
		<li><a href="<c:url value="/" />" >&lt; Volver al balance</a></li>
	</ul>
	<p>
		<jsp:include page="year-selector.jsp" >
			<jsp:param value="/invoices/" name="url"/>
		</jsp:include>
	</p>
	<table class="table">
		<tr>
			<th>Fecha factura</th>
			<th>Proveedor</th>
			<th>Importe</th>
			<th>Afecta al saldo</th>
			<th>Pagador</th>
			<th>Fecha de abono</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${invoicesList}" var="curInvoice">
			<tr>
				<td>
					<fmt:formatDate type ="time" value = "${curInvoice.invoiceDate}" pattern = "dd/MM/yyyy"/>
				</td>
				<td>${curInvoice.provider}</td>
				<td>${curInvoice.amount}</td>
				<td>${curInvoice.balanceDiscount}</td>
				<td>${curInvoice.payer}</td>
				<td>
					<fmt:formatDate type ="time" value = "${curInvoice.paymentDate}" pattern = "dd/MM/yyyy"/>
				</td>
				<td>
					<a href="<c:url value="/invoices/${curInvoice.id}" />">&gt; show invoice</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>