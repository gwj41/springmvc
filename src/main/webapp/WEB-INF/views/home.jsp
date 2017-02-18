<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Home</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

<%--    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<spring:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css"/>
    <script type="text/javascript" src="<spring:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
    <script type="text/javascript" language="JavaScript" src="<spring:url value="/resources/jslib/jquery-3.1.1.js"/>"></script>--%>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>Welcome Home:)</h1>
    <table>
        <tr>
            <td>First name: </td><td>${user.firstName}</td>
        </tr>
        <tr>
            <td>Last name: </td><td>${user.lastName}</td>
        </tr>
        <tr>
            <td>Birthday: </td><td>${user.birthday}</td>
        </tr>
    </table>
<a href="<spring:url value="/mvc/robbie/register"/>">register</a>
</body>
</html>
