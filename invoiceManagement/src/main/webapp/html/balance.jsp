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
	<h1>Balance</h1>
	<table class="table">
		<tr>
			<th>Fecha documento</th>
			<th>Descripción</th>
			<th>Importe</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${balanceDocuments}" var="curDocument">
			<tr>
				<td>
					<fmt:formatDate type ="time" value = "${curDocument.documentDate}" pattern = "dd/MM/yyyy"/>
				</td>
				<td>${curDocument.description}</td>
				<td>${curDocument.amount}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>