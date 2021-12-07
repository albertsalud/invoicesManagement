<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

let euroUE = Intl.NumberFormat("de-DE", {
    style: "currency",
    currency: "EUR",
});

function setValues(subventionDiscount, balanceDiscount){
	var pendingSubvention = ${configuration.subvention} - subventionDiscount;
	if(pendingSubvention < 0) pendingSubvention = 0;
	
	var currentBalance = ${configuration.initialBalance} - balanceDiscount;
	
	$("#pendingSubvention").html(euroUE.format(pendingSubvention));
	$("#currentBalance").html(euroUE.format(currentBalance));
}

function setYear(){
	var year = $("#year-selector").val();
	$(location).attr('href', "<c:url value="/" />" + "?year=" + year);
}
</script>
</head>
<body>
	<h1>Balance</h1>
	<p>
		<select onchange="setYear()" id="year-selector">
			<c:forEach items="${existentConfigurations}" var="curConfiguration">
				<c:set var="selected" value="${param.year == curConfiguration.year ? 'selected' : ''}" />
				<option <c:out value="${selected}" /> value="${curConfiguration.year}">${curConfiguration.year}</option>
			</c:forEach>
		</select>
	</p>
	<table>
		<tr>
			<th>Subvención Anual:</th>
			<td>${configuration.subvention}</td>
			<th>Saldo inicial:</th>
			<td>${configuration.initialBalance}</td>
		</tr>
		<tr>
			<th>Subvención pendiente:</th>
			<td id="pendingSubvention">1000€</td>
			<th>Saldo actual:</th>
			<td id="currentBalance">500€</td>
		</tr>
	</table>
	<ul>
		<li><a href="<c:url value="/configurations" />">&gt; Configuraciones</a></li>
		<li><a href="<c:url value="/invoices" />">&gt; Facturas</a></li>
		<li><a href="<c:url value="/expenses" />">&gt; Gastos</a></li>	
	</ul>
	<table class="table">
		<tr>
			<th>Fecha documento</th>
			<th>Tipo</th>
			<th>Descripción</th>
			<th>Importe</th>
			<th>Afecta saldo</th>
		</tr>
		<c:set var="subventionDiscount" value="0" />
		<c:set var="balanceDiscount" value="0" />
		<c:forEach items="${balanceDocuments}" var="curDocument">
			<tr>
				<td>
					<fmt:formatDate type ="time" value = "${curDocument.documentDate}" pattern = "dd/MM/yyyy"/>
				</td>
				<td>${curDocument.getDocumentType()}</td>
				<td>${curDocument.description}</td>
				<td>${curDocument.amount}</td>
				<td>${curDocument.isBalanceDiscount()}</td>
			</tr>
			<c:if test="${curDocument.isSubventionDiscount()}">
				<c:set var="subventionDiscount" value="${subventionDiscount + curDocument.amount }"/>
			</c:if>
			<c:if test="${curDocument.isBalanceDiscount()}">
				<c:set var="balanceDiscount" value="${balanceDiscount + curDocument.amount }"/>
			</c:if>
		</c:forEach>
	</table>
	<script type="text/javascript">
	
	setValues(${subventionDiscount}, ${balanceDiscount});
	
	</script>
</body>
</html>