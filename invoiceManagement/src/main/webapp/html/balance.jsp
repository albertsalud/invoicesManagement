<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
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

function format(targetId){
	$("#" + targetId).html(
		euroUE.format(
			$("#" + targetId).html()
		)
	);
}

</script>
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Balance</h1>
			<p>
				<jsp:include page="year-selector.jsp">
					<jsp:param value="/" name="url"/>
				</jsp:include>
			</p>
			<table id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Subvención anual:</th>
					<td id="subvention" class="currency">${configuration.subvention}</td>
					<th>Saldo inicial:</th>
					<td id="initialBalance" class="currency">${configuration.initialBalance}</td>
				</tr>
				<tr>
					<th>Subvención pendiente:</th>
					<td id="pendingSubvention" class="currency">1000€</td>
					<th>Saldo actual:</th>
					<td id="currentBalance" class="currency">500€</td>
				</tr>
			</table>
			<p><a href="<c:url value="/configurations" />">&gt; Configuraciones</a></p>
			<p><a href="<c:url value="/invoices" />">&gt; Facturas</a></p>
			<p><a href="<c:url value="/expenses" />">&gt; Gastos</a></p>	
			<table id="data-table" cellpadding="5" cellspacing="0" class="table">
				<tr>
					<th>Fecha documento</th>
					<th>Tipo</th>
					<th>Descripción</th>
					<th>Importe</th>
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
						<td class="currency">
							<fmt:formatNumber type = "currency" minFractionDigits = "2" value = "${curDocument.amount}" />
						</td>
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
			format("subvention");
			format("initialBalance");
			</script>
		</div>
	</div>
</body>
</html>