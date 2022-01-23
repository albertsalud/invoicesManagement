<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Listado de configuraciones</h1>
			<p><a href="<c:url value="/configurations/new" />">&gt; Añadir nueva configuración</a></p>
			<p><a href="<c:url value="/" />" >&lt; Volver al balance</a></p>
			<table class="table" id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Año</th>
					<th>Saldo inicial</th>
					<th>Subvención</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${configurations}" var="curConfiguration">
					<tr>
						<td>${curConfiguration.year}</td>
						<td>${curConfiguration.initialBalance}</td>
						<td>${curConfiguration.subvention}</td>
						<td>
							<a href="<c:url value="/configurations/${curConfiguration.year}" />">&gt; Modificar</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>