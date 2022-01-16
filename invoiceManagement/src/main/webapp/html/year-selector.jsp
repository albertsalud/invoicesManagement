<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
function setYear(){
	var year = $("#year-selector").val();
	$(location).attr('href', "<c:url value="${param.url}" />" + "?year=" + year);
}
</script>
Año: <select onchange="setYear()" id="year-selector">
	<c:forEach items="${existentConfigurations}" var="curConfiguration">
		<c:set var="selected" value="${selectedYear == curConfiguration.year ? 'selected' : ''}" />
		<option <c:out value="${selected}" /> value="${curConfiguration.year}">${curConfiguration.year}</option>
	</c:forEach>
</select>