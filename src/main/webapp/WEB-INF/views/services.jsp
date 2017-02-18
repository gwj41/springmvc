<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kevin's Auto Service Center</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="row">
			<h1>Services</h1>
			<ul class="list-group">
				<li class="list-group-item">Battery Change</li>
				<li class="list-group-item">Oil Change</li>
				<li class="list-group-item">Tire Change</li>					
			</ul>
		</div>
	</div>
</body>
</html>