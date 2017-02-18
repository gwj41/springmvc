<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kevin's Auto Service Center</title>
<%--	<link rel="stylesheet" href="<spring:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css"/>
	<script type="text/javascript" src="<spring:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>--%>
<script>
	var root = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="row">
			<h1>Appointment</h1>
		</div>
		<ul class="list-group">
			<li class="list-group-item"><label>Customer:</label><span>Robbie Gu</span></li>
			<li class="list-group-item"><label>Appointment Date:</label><span>2010/12/13</span></li>
			<li class="list-group-item"><label>Make:</label><span>GM</span></li>
			<li class="list-group-item"><label>Model:</label><span>TRAX</span></li>
			<li class="list-group-item"><label>Year:</label><span>2009</span></li>
		</ul>
	</div>

</body>
</html>