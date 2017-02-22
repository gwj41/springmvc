<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>All Dealerships</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<table class="table-responsive">
    <tr>
        <td>ID</td>
        <td>Dealership Name</td>
        <td>Dealership Brand</td>
        <td>Dealership Phone Number</td>
    </tr>
    <c:forEach items="${dealerships}" var="dealership">
        <tr>
            <td><a href="<spring:url value="/mvc/dealer/dealership/${dealership.id}"/>">${dealership.id}</a></td>
            <td>${dealership.name}</td>
            <td>${dealership.brand}</td>
            <td>${dealership.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
