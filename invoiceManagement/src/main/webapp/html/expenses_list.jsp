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
			<h1>Listado de gastos</h1>
			<p>
				<jsp:include page="year-selector.jsp">
					<jsp:param value="/expenses/" name="url"/>
				</jsp:include>
			</p>
			<p><a href="<c:url value="/expenses/new" />">&gt; Añadir nuevo gasto</a></p>
			<p><a href="<c:url value="/" />" >&lt; Volver al balance</a></p>
			<table class="table" id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Fecha gasto</th>
					<th>Descripción</th>
					<th>Importe</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${expensesList}" var="curExpense">
					<tr>
						<td>
							<fmt:formatDate type ="time" value = "${curExpense.expenseDate}" pattern = "dd/MM/yyyy"/>
						</td>
						<td>${curExpense.description}</td>
						<td>${curExpense.amount}</td>
						<td>
							<a href="<c:url value="/expenses/${curExpense.id}" />">&gt; Modificar</a>
							<a onclick="return askForDelete()" href="<c:url value="/expenses/delete?expense=${curExpense.id}" />">&gt; Eliminar</a>
							<c:if test="${not empty curExpense.documentName}">
								<a target="_blank" href="https://daudecinc.tk/uploaded/${curExpense.year}/Gasto/${curExpense.documentName}">&gt; Ver documento</a>
							</c:if>					
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>