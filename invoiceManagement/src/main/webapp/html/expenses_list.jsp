<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
function askForDelete(){
	return confirm("Se eliminará el elemento seleccionado. ¿Desea continuar?");
}

</script>
</head>
<body>
	<h1>Listado de gastos</h1>
	<p>
		<jsp:include page="year-selector.jsp">
			<jsp:param value="/expenses/" name="url"/>
		</jsp:include>
	</p>
	<ul>
		<li><a href="<c:url value="/expenses/new" />">&gt; Añadir nuevo gasto</a></li>
		<li><a href="<c:url value="/" />" >&lt; Volver al balance</a></li>
	</ul>
	<table class="table">
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
					<a href="<c:url value="/expenses/${curExpense.id}" />">&gt; Ver gasto</a>
					<a onclick="return askForDelete()" href="<c:url value="/expenses/delete?expense=${curExpense.id}" />">&gt; Eliminar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>