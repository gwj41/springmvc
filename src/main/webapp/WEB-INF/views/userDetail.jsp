<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Detail</title>
</head>
<body>
    First Name is ${user.firstName}<br/>
    Last Name is ${user.lastName}<br/>
    Birthday is ${user.birthday}<br/>
    <a href="<spring:url value="/mvc/robbie/save"/>">Confirm</a>

</body>
</html>
